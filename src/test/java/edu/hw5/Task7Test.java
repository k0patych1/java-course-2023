package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task7Test {
    static Arguments[] validFirstPatternArguments() {
        return new Arguments[]{
            Arguments.of("010000"),
            Arguments.of("010"),
            Arguments.of("1101"),
        };
    }

    static Arguments[] invalidFirstPatternArguments() {
        return new Arguments[]{
            Arguments.of("011000"),
            Arguments.of("00"),
            Arguments.of("001"),
        };
    }

    @ParameterizedTest
    @MethodSource("validFirstPatternArguments")
    public void firstPatternValidTest(String s) {
        assertTrue(Task7.isMatchFirstPattern(s));
    }

    @ParameterizedTest
    @MethodSource("invalidFirstPatternArguments")
    public void firstPatternInvalidTest(String s) {
        assertFalse(Task7.isMatchFirstPattern(s));
    }

    static Arguments[] validSecondPatternArguments() {
        return new Arguments[]{
            Arguments.of("0"),
            Arguments.of("1"),
            Arguments.of("11"),
            Arguments.of("00"),
            Arguments.of("10101111"),
            Arguments.of("001111010100"),
        };
    }

    static Arguments[] invalidSecondPatternArguments() {
        return new Arguments[]{
            Arguments.of("01"),
            Arguments.of("001"),
            Arguments.of("011001"),
            Arguments.of("10110"),
        };
    }

    @ParameterizedTest
    @MethodSource("validSecondPatternArguments")
    public void secondPatternValidTest(String s) {
        assertTrue(Task7.isMatchSecondPattern(s));
    }

    @ParameterizedTest
    @MethodSource("invalidSecondPatternArguments")
    public void secondPatternInvalidTest(String s) {
        assertFalse(Task7.isMatchSecondPattern(s));
    }

    static Arguments[] validThirdPatternArguments() {
        return new Arguments[]{
            Arguments.of("0"),
            Arguments.of("1"),
            Arguments.of("11"),
            Arguments.of("00"),
            Arguments.of("111"),
            Arguments.of("011"),
        };
    }

    static Arguments[] invalidThirdPatternArguments() {
        return new Arguments[]{
            Arguments.of(""),
            Arguments.of("1101"),
            Arguments.of("010100"),
            Arguments.of("0000000"),
        };
    }

    @ParameterizedTest
    @MethodSource("validThirdPatternArguments")
    public void thirdPatternValidTest(String s) {
        assertTrue(Task7.isMatchThirdPattern(s));
    }

    @ParameterizedTest
    @MethodSource("invalidThirdPatternArguments")
    public void thirdPatternInvalidTest(String s) {
        assertFalse(Task7.isMatchThirdPattern(s));
    }
}
