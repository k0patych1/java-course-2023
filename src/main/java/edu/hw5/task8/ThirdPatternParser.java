package edu.hw5.task8;

public final class ThirdPatternParser {
    private ThirdPatternParser() {}

    private static final String PATTERN = "(1*01*01*01*)*1*";

    public static boolean isMatch(String str) {
        return str.matches(PATTERN);
    }
}
