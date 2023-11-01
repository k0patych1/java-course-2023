package edu.hw3;

import org.junit.jupiter.api.Test;
import static edu.hw3.Task4.convertToRoman;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task4Test {
    @Test
    public void convertNegativeNumberToRoman() {
        int arabic = -1;

        assertThrows(IllegalArgumentException.class, () -> convertToRoman(arabic));
    }

    @Test
    public void convertSingleDigitNumberToRoman() {
        int arabic = 2;

        assertThat(convertToRoman(arabic)).isEqualTo("II");
    }

    @Test
    public void convertTwoDigitNumberToRoman() {
        int arabic = 12;

        assertThat(convertToRoman(arabic)).isEqualTo("XII");
    }

    @Test
    public void convertThreeDigitNumberToRoman() {
        int arabic = 154;

        assertThat(convertToRoman(arabic)).isEqualTo("CLIV");
    }

    @Test
    public void convertFourDigitNumberToRoman() {
        int arabic = 3678;

        assertThat(convertToRoman(arabic)).isEqualTo("MMMDCLXXVIII");
    }

    @Test
    public void convertTooBigNumberToRoman() {
        int arabic = 4000;

        assertThrows(IllegalArgumentException.class, () -> convertToRoman(arabic));
    }
}
