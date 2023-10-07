package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public final class Task1Test {
    private Task1Test() {}

    @Test
    @DisplayName("Simple correct format")
    void countSimpleLengthOfVideo() {
        String s = "12:34";

        long received_Length = Task1.countLengthOfVideo(s);
        long expected_Length = 12 * 60 + 34;

        assertThat(received_Length).isEqualTo(expected_Length);
    }

    @Test
    @DisplayName("Simple incorrect format : can't parse to numbers")
    void countLengthOfVideoWithExtraChars() {
        String s = "12:3.4";

        long received_Length = Task1.countLengthOfVideo(s);

        assertThat(received_Length).isEqualTo(-1);
    }

    @Test
    @DisplayName("Correct format with zeroes")
    void countLengthOfVideoWithZeroes() {
        String s = "04:20";

        long received_Length = Task1.countLengthOfVideo(s);
        long expected_Length = 4 * 60 + 20;

        assertThat(received_Length).isEqualTo(expected_Length);
    }

    @Test
    @DisplayName("Incorrect format : no symbol ':'")
    void countLengthOfVideoWithoutDividingSymbol() {
        String s = "239";

        long received_Length = Task1.countLengthOfVideo(s);

        assertThat(received_Length).isEqualTo(-1);
    }

    @Test
    @DisplayName("Incorrect format : symbol ':' occurs more than once")
    void countLengthOfVideoWithTooManyDividingSymbols() {
        String s = "10:12:25";

        long received_Length = Task1.countLengthOfVideo(s);

        assertThat(received_Length).isEqualTo(-1);
    }

    @Test
    @DisplayName("Correct format with many minutes (>= 60)")
    void countLengthOfVideoWithManyMinutes() {
        String s = "666:00";

        long received_Length = Task1.countLengthOfVideo(s);
        long expected_Length = 666 * 60;

        assertThat(received_Length).isEqualTo(expected_Length);
    }

    @Test
    @DisplayName("Incorrect format with too many  seconds")
    void countLengthOfVideoWithTooManySeconds() {
        String s = "00:666";
        long received_Length = Task1.countLengthOfVideo(s);

        assertThat(received_Length).isEqualTo(-1);
    }

    @Test
    @DisplayName("Incorrect format with too negative seconds")
    void countLengthOfVideoWithNegativeSeconds() {
        String s = "02:-28";
        long received_Length = Task1.countLengthOfVideo(s);

        assertThat(received_Length).isEqualTo(-1);
    }
}
