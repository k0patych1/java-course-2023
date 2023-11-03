package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Task3 {
    private Task3() {}

    public static <T> Map<T, Integer> freqDict(List<T> objs) {
        Map<T, Integer> freqDict = new HashMap<>();
        objs.forEach(obj -> freqDict.merge(obj, 1, Integer::sum));

        return freqDict;
    }
}
