package edu.hw7;

import edu.hw7.Task1.Main;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    @Test
    @DisplayName("Проверка работоспособности метода")
    public void testParallelFactorial() throws InterruptedException {
        //given
        int number = 7;
        BigInteger answer = BigInteger.valueOf(5040);

        //when
        BigInteger result = Task2.calculateFactorial(number);

        //then
        assertEquals(result, answer);
    }

    @Test
    @DisplayName("Проверка работоспособности метода на больших значениях")
    public void testParallelFactorialBigValues() throws InterruptedException {
        //given
        int number = 25;
        BigInteger answer = new BigInteger("15511210043330985984000000");

        //when
        BigInteger result = Task2.calculateFactorial(number);

        //then
        assertEquals(result, answer);
    }
}
