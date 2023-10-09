package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class Task7Test {
    @Test
    @DisplayName("Test 3 from example (left shift)")
    void rotateLeftExample() {
        int number = 17;
        int shift = 2;

        int receivedAns = Task7.rotateLeft(number, shift);
        int expectedAns = 6;

        assertThat(receivedAns).isEqualTo(expectedAns);
    }

    @Test
    @DisplayName("Negative left shift (positive right)")
    void rotateNegativeLeftShift() {
        int number = 8;
        int shift = -1;

        int receivedAns = Task7.rotateLeft(number, shift);
        int expectedAns = 4;

        assertThat(receivedAns).isEqualTo(expectedAns);
    }

    @Test
    @DisplayName("Random test (right shift)")
    void rotateRightShift() {
        int number = 43;
        int shift = 3;

        int receivedAns = Task7.rotateRight(number, shift);
        int expectedAns = 29; // 101011 -> 011 + 101 -> 01101

        assertThat(receivedAns).isEqualTo(expectedAns);
    }

    @Test
    @DisplayName("Big right shift")
    void rotateBigRightShift() {
        int number = 43;
        int shift = 123;

        int receivedAns = Task7.rotateRight(number, shift);
        int expectedAns = 29; // 101011 -> 011 + 101 -> 01101

        assertThat(receivedAns).isEqualTo(expectedAns);
    }

    @Test
    @DisplayName("Big left shift")
    void rotateBigLeftShift() {
        int number = 17;
        int shift = 1002;

        int receivedAns = Task7.rotateLeft(number, shift);
        int expectedAns = 6;

        assertThat(receivedAns).isEqualTo(expectedAns);
    }

    @Test
    @DisplayName("Negative right shift (positive left)")
    void rotateNegativeRightShift() {
        int number = 16;
        int shift = -1;

        int receivedAns = Task7.rotateRight(number, shift);
        int expectedAns = 1;

        assertThat(receivedAns).isEqualTo(expectedAns);
    }

    @Test
    @DisplayName("Zero right shift")
    void rotateZeroRightShift() {
        int number = 66;
        int shift = 0;

        int receivedAns = Task7.rotateRight(number, shift);
        int expectedAns = 66;

        assertThat(receivedAns).isEqualTo(expectedAns);
    }

    @Test
    @DisplayName("Zero left shift")
    void rotateZeroLeftShift() {
        int number = 77;
        int shift = 0;

        int receivedAns = Task7.rotateLeft(number, shift);
        int expectedAns = 77;

        assertThat(receivedAns).isEqualTo(expectedAns);
    }

    @Test
    @DisplayName("Expected exception - the number must be positive")
    void rotateLeftNegativeNum() {
        int number = -8;
        int shift = 3;

        assertThrows(IllegalArgumentException.class, () -> Task7.rotateLeft(number, shift));
    }
}
