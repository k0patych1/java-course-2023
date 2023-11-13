package edu.hw5.task8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThirdPatternParserTest {
    static Arguments[] validThirdPatternArguments() {
        return new Arguments[]{
            Arguments.of("000"),
            Arguments.of("1011100"),
            Arguments.of("0110110"),
            Arguments.of("10111101011"),
            Arguments.of("111010101001101"),
            Arguments.of("1111"),
            Arguments.of(""),
        };
    }

    static Arguments[] invalidThirdPatternArguments() {
        return new Arguments[]{
            Arguments.of("00"),
            Arguments.of("1101"),
            Arguments.of("10110100"),
            Arguments.of("01101"),
        };
    }

    @ParameterizedTest
    @MethodSource("validThirdPatternArguments")
    public void thirdPatternValidTest(String s) {
        assertTrue(ThirdPatternParser.isMatch(s));
    }

    @ParameterizedTest
    @MethodSource("invalidThirdPatternArguments")
    public void thirdPatternInvalidTest(String s) {
        assertFalse(ThirdPatternParser.isMatch(s));
    }
}
