package edu.hw10.Task2;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {
    private final Object target;
    private final boolean persistCache;
    private final Map<String, Map<Integer, Object>> cache = new HashMap<>();


    private CacheProxy(Object target, boolean persistCache) {
        this.target = target;
        this.persistCache = persistCache;
        if (persistCache) {
            loadCacheFromFile();
        }
    }

    public static <T> T create(Object target, Class<T> interfaceType) {
        boolean persistCache = Arrays.stream(interfaceType.getDeclaredMethods())
            .anyMatch(method -> method.isAnnotationPresent(Cache.class) && method.getAnnotation(Cache.class).persist());

        return interfaceType.cast(Proxy.newProxyInstance(
            interfaceType.getClassLoader(),
            new Class<?>[]{interfaceType},
            new CacheProxy(target, persistCache)
        ));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Cache.class) || !persistCache) {
            return method.invoke(target, args);
        }

        Cache cacheAnnotation = method.getAnnotation(Cache.class);
        String methodKey = method.getName();
        Map<Integer, Object> methodCache = cache.computeIfAbsent(methodKey, k -> new HashMap<>());
        int key = Arrays.hashCode(args);

        if (methodCache.containsKey(key)) {
            return methodCache.get(key);
        }

        Object result = method.invoke(target, args);

        if (cacheAnnotation.persist()) {
            try (PrintWriter writer = createPrintWriter(methodKey)) {
                persistCache(key, result, writer);
            }
            methodCache.put(key, result);
        }

        return result;
    }

    private void persistCache(int key, Object result, PrintWriter writer) {
        writer.println(key + "=" + result);
        writer.flush();
    }

    private PrintWriter createPrintWriter(String methodKey) throws IOException {
        String filePath = getCacheFilePath(methodKey);
        return new PrintWriter(new FileWriter(filePath, true));
    }

    private String getCacheFilePath(String methodKey) {
        return Paths.get(".", methodKey + ".txt").toString();
    }

    private void loadCacheFromFile() {
        String methodKey = "fib";
        Path path = Paths.get(getCacheFilePath(methodKey));
        if (Files.exists(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                Map<Integer, Object> methodCache = cache.computeIfAbsent(methodKey, k -> new HashMap<>());
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("=");
                    if (parts.length == 2) {
                        int key = Integer.parseInt(parts[0]);
                        long value = Long.parseLong(parts[1]);
                        methodCache.put(key, value);
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
