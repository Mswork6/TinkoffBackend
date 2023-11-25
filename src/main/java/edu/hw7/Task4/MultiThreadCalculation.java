package edu.hw7.Task4;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MultiThreadCalculation {

    @SuppressWarnings("MagicNumber")
    public static double calculateMultiThreadPi(int threadAmount, int iterations) {
        AtomicInteger counter = new AtomicInteger(0);
        ThreadCounter[] threadArray = new ThreadCounter[threadAmount];

        for (int i = 0; i < threadAmount; i++) {
            threadArray[i] = new ThreadCounter(counter, iterations / threadAmount);
            threadArray[i].start();
        }

        for (int i = 0; i < threadAmount; i++) {
            try {
                threadArray[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return 4 * ((double) counter.get() / iterations);
    }
}
