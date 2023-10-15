package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    @DisplayName("Обработка не четырёхзначного числа №1")
    void calculateLessFourDigitNumber() {
        // given
        int value = 234;

        // when
        int result = Task6.countK(value);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Обработка не четырёхзначного числа №2")
    void calculateMoreFourDigitNumber() {
        // given
        int value = 812347324;

        // when
        int result = Task6.countK(value);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Обработка отрицательного числа")
    void calculateNegativeNumber() {
        // given
        int value = -3273;

        // when
        int result = Task6.countK(value);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Обработка числа с одинаковыми цифрами")
    void calculateAllEqualsNumber() {
        // given
        int value = 4444;

        // when
        int result = Task6.countK(value);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Обработка корректного числа")
    void calculateCorrectNumber() {
        // given
        int value = 6554;

        // when
        int result = Task6.countK(value);

        // then
        assertThat(result).isEqualTo(4);
    }

}
