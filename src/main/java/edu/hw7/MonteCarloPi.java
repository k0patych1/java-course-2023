package edu.hw7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public final class MonteCarloPi {
    private static final int NUM_THREADS = 10; //m1 pro num of cores
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

        return MONTE_CARLO_NUMBER * (circleCount / (double) iterations);
    }

    public static double calculatePiBySeveralThreads(long iterations) throws InterruptedException {
        AtomicLong circleCount = new AtomicLong(0);
        try (ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS)) {
            for (int i = 0; i < NUM_THREADS; ++i) {
                executor.submit(() -> {
                    long circleCountInThisThread = 0;

                    for (long j = 0; j < iterations / NUM_THREADS; ++j) {
                        double x = ThreadLocalRandom.current().nextDouble();
                        double y = ThreadLocalRandom.current().nextDouble();

                        double distance = Math.hypot(x, y);
                        if (distance <= 1) {
                            ++circleCountInThisThread;
                        }
                    }

                    circleCount.addAndGet(circleCountInThisThread);
                });
            }

            executor.shutdown();
        }

        return MONTE_CARLO_NUMBER * circleCount.get() / iterations;
    }
}
