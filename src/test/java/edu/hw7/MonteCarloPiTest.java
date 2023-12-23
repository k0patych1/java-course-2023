package edu.hw7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonteCarloPiTest {
    private static final double ACCURACY = 0.001;

//    @ParameterizedTest
//    @ValueSource(ints = {10_000_000, 100_000_000, 1_000_000_000})
//    public void MonteCarloPiPerformanceTest(int iterations) {
//        long timeStartSingleThread = System.nanoTime();
//        double receivedBySingleThreadPi = MonteCarloPi.calculatePiByOneThread(iterations);
//        long timeEndSingleThread = System.nanoTime();
//
//        double receivedByMultiThreadPi = MonteCarloPi.calculatePiBySeveralThreads(iterations);
//        long timeEndMultiThread = System.nanoTime();
//
//        assertTrue(Math.abs(Math.PI - receivedByMultiThreadPi) < ACCURACY);
//        assertTrue(Math.abs(Math.PI - receivedBySingleThreadPi) < ACCURACY);
//        assertTrue(timeEndSingleThread - timeStartSingleThread > timeEndMultiThread - timeEndSingleThread);
//    }
}
