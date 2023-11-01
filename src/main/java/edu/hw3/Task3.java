package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Task3 {
    private Task3() {}

    public static <T> Map<T, Integer> freqDict(List<T> objs) {
        Map<T, Integer> freqDict = new HashMap<>();
        for (T obj : objs) {
            freqDict.put(obj, freqDict.getOrDefault(obj, 0) + 1);
        }

        return freqDict;
    }
}
