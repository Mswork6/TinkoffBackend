package edu.hw7.Task1;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("MagicNumber")
public class Main {
    public static int increaseCounter(int threadsAmount) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        IncrementThread[] threads = new IncrementThread[threadsAmount];

        for (int i = 0; i < threadsAmount; i++) {
            threads[i] = new IncrementThread(counter, 1000);
            threads[i].start();
        }

        for (int i = 0; i < threadsAmount; i++) {
            threads[i].join();
        }

        return counter.get();
    }
}
