package edu.hw7.Task1;

public class Main {
    public static int increaseCounter(int threadsAmount) throws InterruptedException {
        Counter counter = new Counter();
        IncrementThread[] threads = new IncrementThread[threadsAmount];

        for (int i = 0; i < threadsAmount; i++) {
            threads[i] = new IncrementThread(counter);
            threads[i].start();
        }

        for (int i = 0; i < threadsAmount; i++) {
            threads[i].join();
        }

        return counter.getCount();
    }
}
