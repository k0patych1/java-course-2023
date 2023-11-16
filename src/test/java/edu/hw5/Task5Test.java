package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {
    static Arguments[] validLicenses() {
        return new Arguments[]{
            Arguments.of("А123ВЕ777"),
            Arguments.of("Р777ОК177"),
            Arguments.of("В777УХ000"),
            Arguments.of("В777ОР77"),
            Arguments.of("В001ОР77"),
        };
    }

    static Arguments[] invalidLicenses() {
        return new Arguments[]{
            Arguments.of("123АВЕ777"),
            Arguments.of("А123ВГ77"),
            Arguments.of("А123ВЕ7777"),
            Arguments.of("А123В777"),
            Arguments.of("В000ОР77"),
        };
    }

    @ParameterizedTest
    @MethodSource("validLicenses")
    public void validLicensePlateTest(String invalidLicense) {
        assertTrue(Task5.isValidLicensePlate(invalidLicense));
    }


    @ParameterizedTest
    @MethodSource("invalidLicenses")
    public void invalidLicensePlateTest(String invalidLicense) {
        assertFalse(Task5.isValidLicensePlate(invalidLicense));
    }
}
