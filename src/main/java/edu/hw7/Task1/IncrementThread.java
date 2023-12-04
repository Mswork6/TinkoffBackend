package edu.hw7.Task1;

import java.util.concurrent.atomic.AtomicInteger;

public class IncrementThread extends Thread {
    private final int iterationAmount;
    private final AtomicInteger counter;

    public IncrementThread(AtomicInteger counter, int iterationAmount) {
        this.counter = counter;
        this.iterationAmount = iterationAmount;
    }

    @Override
    public void run() {
        for (int i = 0; i < iterationAmount; i++) {
            counter.incrementAndGet();
        }
    }
}
