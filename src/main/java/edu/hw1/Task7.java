package edu.hw1;

public final class Task7 {
    private Task7() {}

    private static final String NEGATIVE_EXCEPTION_DESCRIPTION = "The number must be positive";

    public static int rotateLeft(int n, int entryShift) throws IllegalArgumentException {
        if (n <= 0) {
            throw new IllegalArgumentException(NEGATIVE_EXCEPTION_DESCRIPTION);
        }

        int shift = entryShift;
        String stringView = Integer.toBinaryString(n);
        int numOfBits = stringView.length();

        if (shift >= 0) {
            shift %= numOfBits;
        } else {
            return rotateRight(n, -shift);
        }

        var stringViewAns = stringView.substring(shift, numOfBits) + stringView.substring(0, shift);

        return Integer.parseInt(stringViewAns, 2);
    }

    public static int rotateRight(int n, int entryShift) throws IllegalArgumentException {
        if (n <= 0) {
            throw new IllegalArgumentException(NEGATIVE_EXCEPTION_DESCRIPTION);
        }

        int shift = entryShift;
        String stringView = Integer.toBinaryString(n);
        int numOfBits = stringView.length();

        if (shift >= 0) {
            shift %= numOfBits;
        } else {
            return rotateLeft(n, -shift);
        }

        var stringViewAns = stringView.substring(numOfBits - shift, numOfBits)
            + stringView.substring(0, numOfBits - shift);

        return Integer.parseInt(stringViewAns, 2);
    }
}
