package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @Test
    @DisplayName("Проверка метода ToString")
    public void testToString() throws InstantiationException, IllegalAccessException {
        String answer = "Hello, ByteBuddy!";

        ByteBuddy byteBuddy = new ByteBuddy();
        Class<?> dynamicType = byteBuddy.subclass(Object.class)
            .method(named("toString")).intercept(FixedValue.value(answer))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();

        Object instance = dynamicType.newInstance();
        String result = instance.toString();

        assertEquals(answer, result);
    }
}
