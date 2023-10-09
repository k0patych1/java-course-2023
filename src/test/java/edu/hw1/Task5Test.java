package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public final class Task5Test {
    @Test
    @DisplayName("Test 1 from example, expected true")
    void isExamplePalindromeDescendant() {
        int number = 11211230;
        boolean receivedAns = Task5.isPalindromeDescendant(number);

        assertThat(receivedAns).isEqualTo(true);
    }

    @Test
    @DisplayName("Test 2 from example, expected true")
    void isExample2PalindromeDescendant() {
        int number = 13001120;
        boolean receivedAns = Task5.isPalindromeDescendant(number);

        assertThat(receivedAns).isEqualTo(true);
    }

    @Test
    @DisplayName("Big digits, expected false because of odd num of digits of descendant")
    void isBigDigitsOddPalindromeDescendant() {
        long number = 989897969594939291L;
        boolean receivedAns = Task5.isPalindromeDescendant(number);

        assertThat(receivedAns).isEqualTo(false);
    }

    @Test
    @DisplayName("Big digits, expected true")
    void isBigDigitsEvenPalindromeDescendant() {
        int number = 99473899;

        boolean receivedAns = Task5.isPalindromeDescendant(number);

        assertThat(receivedAns).isEqualTo(true);
    }

    @Test
    @DisplayName("single-digit test, expected true")
    void isSingleDigitPalindromeDescendant() {
        int number = 3;

        boolean receivedAns = Task5.isPalindromeDescendant(number);

        assertThat(receivedAns).isEqualTo(true);
    }
}
