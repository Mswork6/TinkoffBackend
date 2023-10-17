package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Вычисление максимального значения")
    void calculateMaxValue() {
        // given
        int value = Integer.MAX_VALUE;

        // when
        int result = Task2.countDigits(value);

        // then
        assertThat(result).isEqualTo(10);
    }

    @Test
    @DisplayName("Вычисление минимального значения")
    void calculateMinValue() {
        // given
        int value = Integer.MIN_VALUE;

        // when
        int result = Task2.countDigits(value);

        // then
        assertThat(result).isEqualTo(10);
    }

    @Test
    @DisplayName("Вычисление нулевого значения")
    void calculateZeroValue() {
        // given
        int value = 0;

        // when
        int result = Task2.countDigits(value);

        // then
        assertThat(result).isEqualTo(1);
    }
}
