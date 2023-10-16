package edu.hw1;

public final class Task2 {
    private Task2() {}

    private static final int NUMBER_SYSTEM = 10;

    public static int countDigits(long entryNumber) {
        long number = entryNumber;
         short counter = 0;
         do {
             number /= NUMBER_SYSTEM;
             ++counter;
         } while (number > 0);

         return counter;
     }
}
