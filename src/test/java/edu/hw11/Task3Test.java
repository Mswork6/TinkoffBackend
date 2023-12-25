package edu.hw11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    @Test
    @DisplayName("Проверка работоспособности")
    public void testFibonacciCalculator() {
        // Создаем экземпляр класса FibCalculator
        Class<?> fibCalculatorClass = Task3.create();

        try {
            // Создаем экземпляр класса FibCalculator
            Object fibCalculator = fibCalculatorClass.newInstance();

            // Вызываем метод fib() для вычисления числа Фибоначчи
            long result = (long) fibCalculatorClass.getDeclaredMethod("fib", int.class).invoke(null, 5);

            // Проверяем, что результат вычисления верный
            assertEquals(5, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    @DisplayName("Проверка работоспособности при больших значениях")
    public void testFibonacciCalculatorWithLargeInput() {
        Class<?> fibCalculatorClass = Task3.create();

        try {
            Object fibCalculator = fibCalculatorClass.newInstance();

            long result = (long) fibCalculatorClass.getDeclaredMethod("fib", int.class).invoke(null, 30);

            assertEquals(832040, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
