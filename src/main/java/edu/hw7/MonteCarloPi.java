package edu.hw7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;

public final class MonteCarloPi {
    private static final double MONTE_CARLO_NUMBER = 4.0;

    private MonteCarloPi() {}

    public static double calculatePiByOneThread(long iterations) {
        long circleCount = 0;

        for (long i = 0; i < iterations; ++i) {
            double x = Math.random();
            double y = Math.random();

            double distance = Math.hypot(x, y);
            if (distance <= 1) {
                ++circleCount;
            }
        }

        return MONTE_CARLO_NUMBER * circleCount / iterations;
    }

    public static double calculatePiBySeveralThreads(long iterations) {
        int numThreads = Runtime.getRuntime().availableProcessors();
        LongAdder circleCount = new LongAdder();
        try (ExecutorService executor = Executors.newFixedThreadPool(numThreads)) {
            for (int i = 0; i < numThreads; ++i) {
                long iterationsPerThread = (i == numThreads - 1)
                    ? iterations / numThreads + iterations % numThreads
                    : iterations / numThreads;
                executor.submit(() -> {
                    long circleCountInThisThread = 0;
                    for (long j = 0; j < iterationsPerThread; ++j) {
                        double x = ThreadLocalRandom.current().nextDouble();
                        double y = ThreadLocalRandom.current().nextDouble();

                        double distance = Math.hypot(x, y);
                        if (distance <= 1) {
                            ++circleCountInThisThread;
                        }
                    }

                    circleCount.add(circleCountInThisThread);
                });
            }
        }

        return MONTE_CARLO_NUMBER * circleCount.sum() / iterations;
    }
}
