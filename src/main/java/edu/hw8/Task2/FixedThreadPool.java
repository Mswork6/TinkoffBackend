package edu.hw8.Task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private final int numThreads;
    private final Thread[] threads;
    private final BlockingQueue<Runnable> taskQueue;

    private FixedThreadPool(int numThreads) {
        this.numThreads = numThreads;
        this.threads = new Thread[numThreads];
        this.taskQueue = new LinkedBlockingQueue<>();
    }

    public static FixedThreadPool create(int numThreads) {
        return new FixedThreadPool(numThreads);
    }

    @Override
    public void start() {
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                while (true) {
                    try {
                        Runnable task = taskQueue.take();
                        task.run();
                    } catch (InterruptedException e) {
                        // Поток был прерван, выходим из цикла
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
            threads[i].start();
        }
    }

    @Override
    public void join() throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            taskQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        // Прерываем все потоки
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
