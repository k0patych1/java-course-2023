package edu.hw5.task8;

public final class FirstPatternParser {
    private FirstPatternParser() {}

    private static final String PATTERN = "([01]{2})*[01]";

    public static boolean isMatch(String str) {
        return str.matches(PATTERN);
    }
}
