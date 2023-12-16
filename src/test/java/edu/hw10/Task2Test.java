package edu.hw10;

import edu.hw10.Task2.CacheProxy;
import edu.hw10.Task2.FibCalculator;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task2Test {

    @Getter private static class TestFibCalculator implements FibCalculator {
        private int fibMethodCallCount = 0;

        @Override
        public long fib(int number) {
            fibMethodCallCount++;
            if (number <= 1) {
                return number;
            } else {
                return fib(number - 1) + fib(number - 2);
            }
        }

    }


    @Test
    @DisplayName("Проверка работоспособности метода")
    public void testCacheProxy() {
        //given

        // Создаем экземпляр калькулятора и его прокси
        TestFibCalculator calculator = new TestFibCalculator();
        FibCalculator proxy = CacheProxy.create(calculator, FibCalculator.class);

        //when

        //then

        // Первый вызов - не из кэша
        long result1 = proxy.fib(5);
        assertEquals(15, calculator.getFibMethodCallCount());

        // Второй вызов - из кэша
        long result2 = proxy.fib(5);
        assertEquals(15, calculator.getFibMethodCallCount());

        // Проверяем, что результаты первого и второго вызовов совпадают
        assertEquals(result1, result2);

        // Вызов с другим аргументом - не из кэша
        long result3 = proxy.fib(8);
        assertEquals(82, calculator.getFibMethodCallCount());

        // Повторный вызов с тем же аргументом - из кэша
        long result4 = proxy.fib(8);
        assertEquals(82, calculator.getFibMethodCallCount());

        // Проверяем, что результаты третьего и четвертого вызовов совпадают
        assertEquals(result3, result4);

        // Рекурсивный вызов - из кэша
        long recursiveResult = proxy.fib(5);
        assertEquals(82, calculator.getFibMethodCallCount());

        File file = Path.of("fib.txt").toFile();
        assertTrue(file.exists());

        if (file.exists()){
            file.delete();
        }
    }
}
