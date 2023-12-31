package edu.hw1;

import java.util.Arrays;

public final class Task6 {
    private Task6() {}

    private static final int KAPREKARS_CONSTANT = 6174;
    private static final int ENTRY_NUMBER_MAX_VALUE = 9999;
    private static final int ENTRY_NUMBER_MIN_VALUE = 1001;

    @SuppressWarnings("MagicNumber")
    private static long newNumber(long number) {
        char[] charArray = Long.toString(number).toCharArray();
        Arrays.sort(charArray);

        long biggestNumber = charArray[0] + charArray[1] * 10 + charArray[2] * 100 + charArray[3] * 1000;
        long smallestNumber = charArray[3] + charArray[2] * 10 + charArray[1] * 100 + charArray[0] * 1000;

        return biggestNumber - smallestNumber;
    }

    private static boolean isAllDigitsTheSame(long number) {
        char[] charArray = Long.toString(number).toCharArray();
        int numOfDigits = charArray.length;

        for (int i = 1; i < numOfDigits; ++i) {
            if (charArray[i] != charArray[i - 1]) {
                return false;
            }
        }

        return true;
    }

    public static int countK(long entryNumber) throws IllegalArgumentException {
        if (entryNumber < ENTRY_NUMBER_MIN_VALUE || entryNumber > ENTRY_NUMBER_MAX_VALUE) {
            throw new IllegalArgumentException("Entry number must be four digits and not equal to 1000");
        }

        if (isAllDigitsTheSame(entryNumber)) {
            throw new IllegalArgumentException("Not all digits in a number should be the same");
        }

        long number = entryNumber;

        int count = 0;

        while (number != KAPREKARS_CONSTANT) {
            number = newNumber(number);
            ++count;
        }

        return count;
    }
}
