package edu.hw7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonteCarloPiTest {
    @ParameterizedTest
    @ValueSource(ints = {10_000_000, 100_000_000, 1_000_000_000})
    public void MonteCarloPiPerformanceTest(int iterations) throws InterruptedException {
        long timeStartSingleThread = System.currentTimeMillis();
        double receivedBySingleThreadPi = MonteCarloPi.calculatePiByOneThread(iterations);
        long timeEndSingleThread = System.currentTimeMillis();

        double receivedByMultiThreadPi = MonteCarloPi.calculatePiBySeveralThreads(iterations);
        long timeEndMultiThread = System.currentTimeMillis();

        assertTrue(Math.abs(Math.PI - receivedByMultiThreadPi) < 1e6);
        assertTrue(Math.abs(Math.PI - receivedBySingleThreadPi) < 1e6);
        assertTrue(timeEndSingleThread - timeStartSingleThread > timeEndMultiThread - timeEndSingleThread);
    }
}
