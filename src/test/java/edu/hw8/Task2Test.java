package edu.hw8;

import edu.hw8.Task2.FixedThreadPool;
import edu.hw8.Task2.ThreadPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static edu.hw8.Task2.Fibonacci.calculateFibonacci;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @Test
    @DisplayName("Проверка корректной работы на примере Фибоначчи")
    void calculateCorrectValue() {
        // given
        List<Long> values = Collections.synchronizedList(new ArrayList<>());

        // when
        try (ThreadPool threadPool = FixedThreadPool.create(6)) {
            // Загрузка задач в очередь перед запуском потоков
            for (int i = 0; i < 10; i++) {
                final int n = i;
                threadPool.execute(() -> {
                    long result = calculateFibonacci(n);
                    values.add(result);
                });
            }
            threadPool.start();
            sleep(1000);
            threadPool.execute(() -> {
                long result = calculateFibonacci(10);
                values.add(result);
            });
            sleep(1000);
            threadPool.execute(() -> {
                long result = calculateFibonacci(11);
                values.add(result);
            });
            threadPool.close();
            threadPool.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Collections.sort(values);

        // then
        assertEquals(values.size(), 12);
        assertEquals(values.get(0), 0);
        assertEquals(values.get(1), 1);
        assertEquals(values.get(2), 1);
        assertEquals(values.get(3), 2);
        assertEquals(values.get(4), 3);
        assertEquals(values.get(5), 5);
        assertEquals(values.get(6), 8);
        assertEquals(values.get(7), 13);
        assertEquals(values.get(8), 21);
        assertEquals(values.get(9), 34);
        assertEquals(values.get(10), 55);
        assertEquals(values.get(11), 89);
    }
}
