package edu.hw5.task8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FifthPatternParserTest {
    static Arguments[] validFifthPatternArguments() {
        return new Arguments[]{
            Arguments.of(""),
            Arguments.of("1"),
            Arguments.of("11"),
            Arguments.of("101"),
            Arguments.of("1010111"),
        };
    }

    static Arguments[] invalidFifthPatternArguments() {
        return new Arguments[]{
            Arguments.of("011"),
            Arguments.of("110"),
            Arguments.of("00000"),
            Arguments.of("11110"),
        };
    }

    @ParameterizedTest
    @MethodSource("validFifthPatternArguments")
    public void fifthPatternValidTest(String s) {
        assertTrue(FifthPatternParser.isMatch(s));
    }

    @ParameterizedTest
    @MethodSource("invalidFifthPatternArguments")
    public void fifthPatternInvalidTest(String s) {
        assertFalse(FifthPatternParser.isMatch(s));
    }
}
