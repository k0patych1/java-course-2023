package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    public void emptyArrayFreqDictTest() {
        List<String> emptyList = new ArrayList<>();
        Map<Integer, Integer> emptyMap = new HashMap<>();

        assertThat(Task3.freqDict(emptyList)).isEqualTo(emptyMap);
    }

    @Test
    public void stringArrayFreqDictTest() {
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("bb");
        strings.add("a");
        strings.add("bb");

        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("bb", 2);
        expectedMap.put("a", 2);

        assertThat(Task3.freqDict(strings)).isEqualTo(expectedMap);
    }

    @Test
    public void integerArrayFreqDictTest() {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(1);
        integers.add(2);
        integers.add(2);

        Map<Integer, Integer> expectedMap = new HashMap<>();
        expectedMap.put(1, 2);
        expectedMap.put(2, 2);

        assertThat(Task3.freqDict(integers)).isEqualTo(expectedMap);
    }
}

