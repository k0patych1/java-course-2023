package edu.hw7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.concurrent.atomic.AtomicInteger;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CounterTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 100, 10_000, 100_000, 1_000_000})
    public void raceConditionTest(int numOfIncrementsInEachThread) {
        Counter counter = new Counter(0);
        AtomicInteger atomicInteger = new AtomicInteger(0);

        Thread firstThread = new Thread(() -> {
            for (int i = 0; i < numOfIncrementsInEachThread; ++i) {
                counter.increment();
                atomicInteger.incrementAndGet();
            }
        });

        Thread secondThread = new Thread(() -> {
            for (int i = 0; i < numOfIncrementsInEachThread; ++i) {
                counter.increment();
                atomicInteger.incrementAndGet();
            }
        });

        Thread thirdThread = new Thread(() -> {
            for (int i = 0; i < numOfIncrementsInEachThread; ++i) {
                counter.increment();
                atomicInteger.incrementAndGet();
            }
        });

        firstThread.start();
        secondThread.start();
        thirdThread.start();

        try {
           firstThread.join();
           secondThread.join();
           thirdThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(counter.getValue()).isEqualTo(atomicInteger.get());
        assertThat(counter.getValue()).isEqualTo(numOfIncrementsInEachThread * 3);
    }
}
