package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {
    @Test
    public void substringTest() {
        String S = "12345";
        String T = "abcf12345";

        assertTrue(Task6.isSubsequence(S, T));
    }

    @Test
    public void subsequenceTest() {
        String S = "1a2b3c4d5";
        String T = "ABOBA1a2b3c4d5ABOBA";

        assertTrue(Task6.isSubsequence(S, T));
    }

    @Test
    public void equalStringsTest() {
        String S = "12345";
        String T = "12345";

        assertTrue(Task6.isSubsequence(S, T));
    }

    @Test
    public void usualTest() {
        String S = "1236";
        String T = "12345";

        assertFalse(Task6.isSubsequence(S, T));
    }
}
