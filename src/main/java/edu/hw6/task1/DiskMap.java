package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class DiskMap implements Map<String, String> {
    private final Path file;
    private final Map<String, String> map;

    public DiskMap(Path file) {
        this.file = file;
        this.map = new HashMap<>();
        loadMapFromFile();
    }

    private void loadMapFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                String key = parts[0];
                String value = parts[1];
                map.put(key, value);
            }
        } catch (IOException ignored) {

        }
    }

    private void saveMapToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile()))) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                String line = key + ":" + value;
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException ignored) {
        }
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Override
    public String put(String key, String value) {
        String previousValue = map.put(key, value);
        saveMapToFile();
        return previousValue;
    }

    @Override
    public String remove(Object key) {
        String removedValue = map.remove(key);
        saveMapToFile();
        return removedValue;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        map.putAll(m);
        saveMapToFile();
    }

    @Override
    public void clear() {
        map.clear();
        saveMapToFile();
    }

    @Override
    public @NotNull Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public @NotNull Collection<String> values() {
        return map.values();
    }

    @Override
    public @NotNull Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }
}
