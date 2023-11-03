package edu.hw3;

import edu.hw3.task7.NullComparator;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    public void isNullContainsInTreeMapTest() {
        Map<String, String> tree = new TreeMap<>(new NullComparator());
        tree.put(null, "test1");

        assertTrue(tree.containsKey(null));
    }

    @Test
    public void correctNullRewriteInTreeMapTest() {
        Map<String, String> tree = new TreeMap<>(new NullComparator());
        tree.put(null, "test1");
        tree.put("key", "value");
        tree.put(null, "test2");

        assertThat(tree.get(null)).isEqualTo("test2");
    }
}
