package edu.hw10.Task2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
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
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(Object target, Class<T> interfaceType) {
        boolean persistCache = Arrays.stream(interfaceType.getDeclaredMethods())
            .anyMatch(method -> method.isAnnotationPresent(Cache.class) && method.getAnnotation(Cache.class).persist());

        return (T) Proxy.newProxyInstance(
            interfaceType.getClassLoader(),
            new Class<?>[]{interfaceType},
            new CacheProxy(target, persistCache)
        );
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

        // Вычисляем значение
        Object result = method.invoke(target, args);

        // Проводим кэширование
        if (cacheAnnotation.persist()) {
            try (PrintWriter writer = createPrintWriter(methodKey)) {
                persistCache(methodKey, args, writer);
            }
            methodCache.put(key, result);
        }

        return result;
    }

    private PrintWriter createPrintWriter(String methodKey) throws IOException {
        String filePath = getCacheFilePath(methodKey);
        return new PrintWriter(new FileWriter(filePath, true));
    }

    private void persistCache(String methodKey, Object[] args, PrintWriter writer) {
        // Добавляем имя метода в файл
        writer.println("Method: " + methodKey);

        // Записываем аргументы
        for (Object arg : args) {
            writer.println("Arg: " + arg);
        }

        // Записываем результат
        writer.println("-----");
        writer.flush();
    }

    private String getCacheFilePath(String methodKey) {
        return Path.of(".", methodKey + ".txt").toString();
    }
}
