package edu.hw10.task2;

public class FibCalculator implements IFibCalculator {
    @Override
    public long fib(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("The input number should be positive.");
        }

        if (number == 1 || number == 2) {
            number = 1;
            return number;
        }

        long[] fibNumbers = new long[number];
        fibNumbers[0] = 1;
        fibNumbers[1] = 1;

        for (int i = 2; i < number; ++i) {
            fibNumbers[i] = fibNumbers[i - 1] + fibNumbers[i - 2];
        }

        return fibNumbers[number - 1];
    }
}
