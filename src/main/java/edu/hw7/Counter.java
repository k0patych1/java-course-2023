package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private final AtomicInteger count;

    public Counter(int value) {
        count = new AtomicInteger(value);
    }

    public void increment() {
        count.incrementAndGet();
    }

    public int getValue() {
        return count.get();
    }
}
