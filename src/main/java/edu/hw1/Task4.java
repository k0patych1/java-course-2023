package edu.hw1;

public final class Task4 {
    private Task4() {}

    private static void swap(char[] charArray, int i, int j) {
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
    }

    public static String fixString(String s) {
        var charArray = s.toCharArray();

        long lenOfString = charArray.length;
        for (int i = 0; i < lenOfString - 1; i += 2) {
            swap(charArray, i, i + 1);
        }

        return String.valueOf(charArray);
    }
}
