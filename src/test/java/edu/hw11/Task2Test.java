package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @Test
    void testArithmetics() throws NoSuchMethodException, InvocationTargetException,
        InstantiationException, IllegalAccessException {
        int result = 40;

        int answer = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(named("sum"))
            .intercept(MethodDelegation.to(new Multiply()))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded()
            .asSubclass(SumInterface.class)
            .getDeclaredConstructor()
            .newInstance()
            .sum(8, 5);
        assertEquals(answer, result);
    }

    // Интерфейс объединяет методы sum из обоих классов
    public interface SumInterface {
        int sum(int a, int b);
    }

    public static class Multiply implements SumInterface {
        public int sum(int a, int b) {
            return a * b;
        }
    }

    public static class ArithmeticUtils implements SumInterface {
        public int sum(int a, int b) {
            return a + b;
        }
    }
}
