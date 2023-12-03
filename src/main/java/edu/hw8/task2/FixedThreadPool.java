package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private static final String CLOSED_THREAD_POOL_EXCEPTION = "Thread pool was closed";
    private final BlockingQueue<Runnable> workQueue;

    private final int threadCount;

    private final Thread[] threads;

    private boolean isClosed;

    public FixedThreadPool(int threadCount) {
        this.threadCount = threadCount;
        threads = new Thread[threadCount];
        workQueue = new LinkedBlockingQueue<>();
        isClosed = false;
    }

    @Override
    public void start() {
        if (isClosed) {
            throw new RuntimeException(CLOSED_THREAD_POOL_EXCEPTION);
        }
        for (int i = 0; i < threadCount; ++i) {
            threads[i] = new Thread(new Worker());
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (isClosed) {
            throw new RuntimeException(CLOSED_THREAD_POOL_EXCEPTION);
        }

        try {
            workQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        if (isClosed) {
            throw new RuntimeException(CLOSED_THREAD_POOL_EXCEPTION);
        }

        isClosed = true;
        while (!workQueue.isEmpty()) {

        }

        for (int i = 0; i < threadCount; ++i) {
            if (threads[i] != null) {
                threads[i].interrupt();
            }
        }
    }

    private class Worker implements Runnable {
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Runnable task = workQueue.take();
                    task.run();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
