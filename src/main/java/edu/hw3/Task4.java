package edu.hw3;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public final class Task4 {

    public static final int FIRST_ROMAN_DIGIT = 1;

    public static final int SECOND_ROMAN_DIGIT = 4;

    public static final int THIRD_ROMAN_DIGIT = 5;

    public static final int FOURTH_ROMAN_DIGIT = 9;

    public static final int FIFTH_ROMAN_DIGIT = 10;

    public static final int SIXTH_ROMAN_DIGIT = 40;

    public static final int SEVENTH_ROMAN_DIGIT = 50;

    public static final int EIGHTH_ROMAN_DIGIT = 90;

    public static final int NINTH_ROMAN_DIGIT = 100;

    public static final int TENTH_ROMAN_DIGIT = 400;

    public static final int ELEVENTH_ROMAN_DIGIT  = 500;

    public static final int TWELFTH_ROMAN_DIGIT = 900;

    public static final int THIRTEENTH_ROMAN_DIGIT = 1000;
    private static final Map<Integer, String> ROMAN_MAP = new TreeMap<>(Comparator.reverseOrder()) {
        {
        put(THIRTEENTH_ROMAN_DIGIT, "M");
        put(TWELFTH_ROMAN_DIGIT, "CM");
        put(ELEVENTH_ROMAN_DIGIT, "D");
        put(TENTH_ROMAN_DIGIT, "CD");
        put(NINTH_ROMAN_DIGIT, "C");
        put(EIGHTH_ROMAN_DIGIT, "XC");
        put(SEVENTH_ROMAN_DIGIT, "L");
        put(SIXTH_ROMAN_DIGIT, "XL");
        put(FIFTH_ROMAN_DIGIT, "X");
        put(FOURTH_ROMAN_DIGIT, "IX");
        put(THIRD_ROMAN_DIGIT, "V");
        put(SECOND_ROMAN_DIGIT, "IV");
        put(FIRST_ROMAN_DIGIT, "I");
        }
    };
    public static final int MAX_ROMAN_NUMBER = 3999;
    public static final int MIN_ROMAN_NUMBER = 1;

    private Task4() {}

    public static String convertToRoman(int entryNum) {
        if (entryNum < MIN_ROMAN_NUMBER || entryNum > MAX_ROMAN_NUMBER) {
            throw new IllegalArgumentException("Number should be between 1 and 3999");
        }

        int numCopy = entryNum;

        StringBuilder romanNumeral = new StringBuilder();

        for (var entry : ROMAN_MAP.entrySet()) {
            int arabic = entry.getKey();
            String roman = entry.getValue();

            while (numCopy >= arabic) {
                romanNumeral.append(roman);
                numCopy -= arabic;
            }
        }

        return romanNumeral.toString();
    }
}
