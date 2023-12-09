package edu.hw7.Task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadCounter extends Thread {
    private final AtomicInteger counter;
    private final int iterations;
    private static final int CIRCLE_RADIUS = 1;
    public static final double CIRCLE_AMOUNT = Math.pow(CIRCLE_RADIUS, 2);

    public ThreadCounter(AtomicInteger counter, int iterations) {
        this.counter = counter;
        this.iterations = iterations;
    }

    private void calculate() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int circlePointCounter = 0;

        for (int i = 0; i < iterations; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();


            if (Math.pow(x, 2) + Math.pow(y, 2) <= CIRCLE_AMOUNT) {
                circlePointCounter++;
            }
        }

        counter.addAndGet(circlePointCounter);
    }

    @Override
    public void run() {
        calculate();
    }
}
