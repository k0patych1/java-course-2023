package edu.hw5.task8;

public final class SecondPatternParser {
    private SecondPatternParser() {}

    private static final String PATTERN = "(0([01]{2})*)|(1([01]{2})*[01])";

    public static boolean isMatch(String str) {
        return str.matches(PATTERN);
    }
}
