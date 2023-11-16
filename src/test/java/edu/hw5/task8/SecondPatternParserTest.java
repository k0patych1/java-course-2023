package edu.hw5.task8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecondPatternParserTest {
    static Arguments[] validSecondPatternArguments() {
        return new Arguments[]{
            Arguments.of("0"),
            Arguments.of("011"),
            Arguments.of("01101"),
            Arguments.of("10"),
            Arguments.of("1111"),
            Arguments.of("10010111"),
        };
    }

    static Arguments[] invalidSecondPatternArguments() {
        return new Arguments[]{
            Arguments.of(""),
            Arguments.of("01"),
            Arguments.of("0101"),
            Arguments.of("1"),
            Arguments.of("110"),
            Arguments.of("1000011"),
        };
    }

    @ParameterizedTest
    @MethodSource("validSecondPatternArguments")
    public void secondPatternValidTest(String s) {
        assertTrue(SecondPatternParser.isMatch(s));
    }

    @ParameterizedTest
    @MethodSource("invalidSecondPatternArguments")
    public void secondPatternInvalidTest(String s) {
        assertFalse(SecondPatternParser.isMatch(s));
    }
}
