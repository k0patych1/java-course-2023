package edu.hw7;

import java.math.BigInteger;
import java.util.stream.IntStream;

public final class ParallelFactorialCalculator {
    private ParallelFactorialCalculator() {}

    public static BigInteger count(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The factorial of a negative number is undefined");
        }

        return IntStream.rangeClosed(1, n)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
