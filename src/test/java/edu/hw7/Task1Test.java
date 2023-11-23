package edu.hw7;

import edu.hw7.Task1.Main;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    @Test
    @DisplayName("Проверка работоспособности метода")
    public void testCounterConcurrency() throws InterruptedException {
        //given
        int threadsAmount = 6;

        //when
        int result = Main.increaseCounter(threadsAmount);

        //then
        assertEquals(result, 6000);
    }
}
