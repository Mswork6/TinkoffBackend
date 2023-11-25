package edu.hw7.Task1;

public class IncrementThread extends Thread {
    private final static int ITERATION_AMOUNT = 1000;
    private final Counter counter;

    public IncrementThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < ITERATION_AMOUNT; i++) {
            counter.increment();
        }
    }
}
