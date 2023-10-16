package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public final class Task2Test {
    private Task2Test() {}

    @Test
    @DisplayName("Check work with a random big number")
    void countDigitsOfBigNumber() {
        long entryNumber = 6_666_666_666_666_666_666L;

        long recievedNumber = Task2.countDigits(entryNumber);
        long expectedNumber = 19;

        assertThat(recievedNumber).isEqualTo(expectedNumber);
    }

    @Test
    @DisplayName("Must return 1 to 1")
    void countDigitsOfZero() {
        long entryNumber = 0;

        long receivedNumber = Task2.countDigits(entryNumber);
        long expectedNumber = 1;

        assertThat(receivedNumber).isEqualTo(expectedNumber);
    }

    @Test
    @DisplayName("Must return 1 to single-digit numbers")
    void countDigitsOfSingleDigitNumber() {
        long entryNumber = 3;

        long receivedNumber = Task2.countDigits(entryNumber);
        long expectedNumber = 1;

        assertThat(receivedNumber).isEqualTo(expectedNumber);
    }
}
