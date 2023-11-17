package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class DiskMap implements Map<String, String> {
    private final Path filePath;
    private final Map<String, String> inMemoryMap;

    public DiskMap(String filePath) {
        this.filePath = Paths.get(filePath);
        this.inMemoryMap = new HashMap<>();
        loadFromFile();
    }

    private void loadFromFile() {
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    inMemoryMap.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения из файла: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try {
            List<String> lines = new ArrayList<>();
            for (Map.Entry<String, String> entry : inMemoryMap.entrySet()) {
                lines.add(entry.getKey() + ":" + entry.getValue());
            }
            Files.write(filePath, lines);
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    @Override
    public int size() {
        return inMemoryMap.size();
    }

    @Override
    public boolean isEmpty() {
        return inMemoryMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return inMemoryMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return inMemoryMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return inMemoryMap.get(key);
    }

    @Override
    public String put(String key, String value) {
        String newValue = inMemoryMap.put(key, value);
        saveToFile();
        return newValue;
    }

    @Override
    public String remove(Object key) {
        String removedValue = inMemoryMap.remove(key);
        saveToFile();
        return removedValue;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        inMemoryMap.putAll(m);
        saveToFile();
    }

    @Override
    public void clear() {
        inMemoryMap.clear();
        saveToFile();
    }

    @Override
    public Set<String> keySet() {
        return inMemoryMap.keySet();
    }

    @Override
    public Collection<String> values() {
        return inMemoryMap.values();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return inMemoryMap.entrySet();
    }
}
