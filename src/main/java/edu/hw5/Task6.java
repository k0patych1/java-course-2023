package edu.hw5;

public final class Task6 {
    private Task6() {}

    public static boolean isSubsequence(String s, String t) {
        String pattern = ".*" + String.join(".*", s.split("")) + ".*";
        return t.matches(pattern);
    }
}
