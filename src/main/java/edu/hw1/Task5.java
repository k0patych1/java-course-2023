package edu.hw1;

public final class Task5 {
    private Task5() {}

    private static boolean isPalindrome(char[] charArrayNumber) {
        int numOfDigits = charArrayNumber.length;

        for (int i = 0; i < numOfDigits / 2; ++i) {
            if (charArrayNumber[i] != charArrayNumber[numOfDigits - 1 - i]) {
                return false;
            }
        }

        return true;
    }

    @SuppressWarnings("MagicNumber")
    public static boolean isPalindromeDescendant(long number) {
        char[] charArrayNumber = Long.toString(number).toCharArray();
        int numOfDigits = charArrayNumber.length;

        if (isPalindrome(charArrayNumber)) {
            return true;
        }

        if (numOfDigits % 2 != 0) {
            return false;
        }

        long descendant = 0;

        for (int i = 0; i < numOfDigits - 1; i += 2) {
            int digit1 = Character.getNumericValue(charArrayNumber[i]);
            int digit2 = Character.getNumericValue(charArrayNumber[i + 1]);

            if (digit1 + digit2 >= 10) {
                descendant *= 100;
            } else {
                descendant *= 10;
            }

            descendant += digit1 + digit2;
        }

        return isPalindromeDescendant(descendant);
    }
}

