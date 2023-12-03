package edu.hw8.task2;

public class Fibonacci {
    public long getNum() {
        return num;
    }

    private final long num;

    public long getValue() {
        return value;
    }

    private long value;

    public Fibonacci(int n) {
        num = n;
    }

    public long calculate(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("The input number should be positive.");
        }

        if (n == 1 || n == 2) {
            value = 1;
            return value;
        }

        long[] fibNumbers = new long[n];
        fibNumbers[0] = 1;
        fibNumbers[1] = 1;

        for (int i = 2; i < n; ++i) {
            fibNumbers[i] = fibNumbers[i - 1] + fibNumbers[i - 2];
        }

        value = fibNumbers[n - 1];

        return value;
    }
}
