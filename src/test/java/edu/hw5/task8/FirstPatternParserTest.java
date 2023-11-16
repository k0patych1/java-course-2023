package edu.hw5.task8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstPatternParserTest {
    static Arguments[] validFirstPatternArguments() {
        return new Arguments[]{
            Arguments.of("1"),
            Arguments.of("010"),
            Arguments.of("111"),
            Arguments.of("11011"),
        };
    }

    static Arguments[] invalidFirstPatternArguments() {
        return new Arguments[]{
            Arguments.of("0110"),
            Arguments.of("00"),
            Arguments.of("011101"),
            Arguments.of("")
        };
    }

    @ParameterizedTest
    @MethodSource("validFirstPatternArguments")
    public void firstPatternValidTest(String s) {
        assertTrue(FirstPatternParser.isMatch(s));
    }

    @ParameterizedTest
    @MethodSource("invalidFirstPatternArguments")
    public void firstPatternInvalidTest(String s) {
        assertFalse(FirstPatternParser.isMatch(s));
    }
}
