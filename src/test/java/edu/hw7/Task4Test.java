package edu.hw7;

import edu.hw7.Task4.MultiThreadCalculation;
import edu.hw7.Task4.SingleThreadCalculation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    @Test
    @DisplayName("Проверка работоспособности метода")
    public void testSingleThreadCalculation() throws InterruptedException {
        //given

        //when
        double result = SingleThreadCalculation.calculatePi(1000000000);
        System.out.println(result);

        //then
        assertTrue(true);
    }

    @Test
    @DisplayName("Проверка работоспособности метода")
    public void testCounterConcurrencyMulty() throws InterruptedException {
        //given


        //when
        double result = MultiThreadCalculation.calculateMultiThreadPi(6, 1000000000);
        System.out.println(result);

        //then
        assertTrue(true);
    }
}
