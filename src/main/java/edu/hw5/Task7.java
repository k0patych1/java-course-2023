package edu.hw5;

public final class Task7 {
    private Task7() {}

    private static final String FIRST_REGEX_PATTERN = "[10]{2}0[10]*";

    private static final String SECOND_REGEX_PATTERN = "([01])[01]*\\1";

    private static final String THIRD_REGEX_PATTERN = "[10]{1,3}";

    public static boolean isMatchFirstPattern(String str) {
        return str.matches(FIRST_REGEX_PATTERN);
    }

    public static boolean isMatchSecondPattern(String str) {
        return str.matches(SECOND_REGEX_PATTERN) || str.length() == 1;
    }

    public static boolean isMatchThirdPattern(String str) {
        return str.matches(THIRD_REGEX_PATTERN);
    }
}

