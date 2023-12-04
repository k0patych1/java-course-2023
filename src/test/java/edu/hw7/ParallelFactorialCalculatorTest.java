package edu.hw7;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParallelFactorialCalculatorTest {
    @Test
    public void negativeNumberTest() {
        assertThrows(IllegalArgumentException.class, () -> ParallelFactorialCalculator.count(-5));
    }

    @Test
    public void zeroNumberTest() {
        assertThat(ParallelFactorialCalculator.count(0)).isEqualTo(BigInteger.ONE);
    }

    @Test
    public void smallNumberTest() {
        assertThat(ParallelFactorialCalculator.count(5)).isEqualTo(BigInteger.valueOf(120));
    }

    @Test
    public void bigNumberTest() {
        assertThat(ParallelFactorialCalculator.count(19)).isEqualTo(BigInteger.valueOf(121_645_100_408_832_000L));
    }
}
