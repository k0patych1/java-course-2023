package edu.hw1;

final class Task2 {
    private Task2() {}

    @SuppressWarnings("MagicNumber")
     public static int countDigits(long entryNumber) {
        long number = entryNumber;
         short counter = 0;
         do {
             number /= 10;
             ++counter;
         } while (number > 0);

         return counter;
     }
}
