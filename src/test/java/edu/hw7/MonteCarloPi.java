package edu.hw7;

import java.util.concurrent.atomic.AtomicLong;

public final class MonteCarloPi {

    private static final int NUM_THREADS = 10; //m1 pro num of cores

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

        return 4.0 * (circleCount / (double) iterations);
    }

    public static double calculatePiBySeveralThreads(long iterations) throws InterruptedException {
        AtomicLong circleCount = new AtomicLong(0);
        AtomicLong totalCount = new AtomicLong(0);

        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; ++i) {
            threads[i] = new Thread(() -> {
                for (long j = 0; j < iterations; ++j) {
                    double x = Math.random();
                    double y = Math.random();

                    double distance = Math.hypot(x, y);
                    if (distance <= 1) {
                        circleCount.incrementAndGet();
                    }

                    totalCount.incrementAndGet();
                }
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return  4.0 * circleCount.get() / totalCount.get();
    }
}
