package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public final class Task4Test {

    @Test
    @DisplayName("Simple case with digits and even number of chars")
    void fixStringWithEvenNumberOfDigits() {
        String s = "21436587";

        String receivedString = Task4.fixString(s);
        String expectedString = "12345678";

        assertThat(receivedString).isEqualTo(expectedString);
    }

    @Test
    @DisplayName("Simple case with digits and od number of chars")
    void fixStringWithOddNumberOfDigits() {
        String s = "214365879";

        String receivedString = Task4.fixString(s);
        String expectedString = "123456789";

        assertThat(receivedString).isEqualTo(expectedString);
    }

    @Test
    @DisplayName("Test from example")
    void fixStringFromExample() {
        String s = "hTsii  s aimex dpus rtni.g";

        String receivedString = Task4.fixString(s);
        String expectedString = "This is a mixed up string.";

        assertThat(receivedString).isEqualTo(expectedString);
    }
}
