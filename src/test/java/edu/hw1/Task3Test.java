package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public final class Task3Test {
    @Test
    @DisplayName("Simple case : array is nestable")
    void trueCaseIsArrayNestable() {
        int[] nestedArr = {1, 2, 3, 4};
        int[] arr = {0, 6};

        boolean receivedAnswer = Task3.isNestable(nestedArr, arr);
        boolean expectedAnswer = true;

        assertThat(receivedAnswer).isEqualTo(expectedAnswer);
    }

    @Test
    @DisplayName("Simple case : array isn't nestable")
    void falseCaseIsArrayNestable() {
        int[] nestedArr = {9, 9, 8};
        int[] arr = {8, 9};

        boolean receivedAnswer = Task3.isNestable(nestedArr, arr);
        boolean expectedAnswer = false;

        assertThat(receivedAnswer).isEqualTo(expectedAnswer);
    }

    @Test
    @DisplayName("Empty array can be nested")
    void isEmptyArrayNestable() {
        int[] nestedArr = {};
        int[] arr = {1, 2, 3, 4};

        boolean receivedAnswer = Task3.isNestable(nestedArr, arr);
        boolean expectedAnswer = true;

        assertThat(receivedAnswer).isEqualTo(expectedAnswer);
    }

    @Test
    @DisplayName("Empty array can be nested into empty array")
    void isEmptyArrayNestableToEmptyArray() {
        int[] nestedArr = {};
        int[] arr = {};

        boolean receivedAnswer = Task3.isNestable(nestedArr, arr);
        boolean expectedAnswer = true;

        assertThat(receivedAnswer).isEqualTo(expectedAnswer);
    }
}
