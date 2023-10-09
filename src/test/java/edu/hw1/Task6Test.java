package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class Task6Test {
    @Test
    @DisplayName("The number is 6174, expected 0")
    void countKOf6174() {
        int number = 6174;

        int receivedNumber = Task6.countK(number);
        int expectedNumber = 0;

        assertThat(receivedNumber).isEqualTo(expectedNumber);
    }

    @Test
    @DisplayName("Test 1 from example")
    void countKFromExample() {
        int number = 6621;

        int receivedNumber = Task6.countK(number);
        int expectedNumber = 5;

        assertThat(receivedNumber).isEqualTo(expectedNumber);
    }

    @Test
    @DisplayName("Test 2 from example")
    void countKFromExample2() {
        int number = 6621;

        int receivedNumber = Task6.countK(number);
        int expectedNumber = 5;

        assertThat(receivedNumber).isEqualTo(expectedNumber);
    }

    @Test
    @DisplayName("Test 2 from example")
    void countKOfNumberWithZeroes() {
        int number = 5000;

        int receivedNumber = Task6.countK(number);
        int expectedNumber = 6;

        assertThat(receivedNumber).isEqualTo(expectedNumber);
    }

    @Test
    @DisplayName("Expected IllegalArgumentException exception - entry number must be four digits")
    void countKOfNotFourDigitNumber() {
        int number = 12321;

        assertThrows(IllegalArgumentException.class, () -> Task6.countK(number));
    }

    @Test
    @DisplayName("Expected IllegalArgumentException exception - not all digits in a number should be the same")
    void countKOfAllDigitsTheSameNumber() {
        int number = 4444;

        assertThrows(IllegalArgumentException.class, () -> Task6.countK(number));
    }
}
