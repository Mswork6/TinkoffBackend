package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Обработка отрицательного числа при сдвиге влево")
    void calculateNegativeNumberLeft() {
        // given
        int value = -32465;

        // when
        int result = Task7.rotateLeft(value, 5);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Обработка корректного числа при сдвиге влево")
    void calculateCorrectNumberLeft() {
        // given
        int value = 2456;

        // when
        int result = Task7.rotateLeft(value, 2);

        // then
        assertThat(result).isEqualTo(1634);
    }

    @Test
    @DisplayName("Обработка отрицательного числа при сдвиге вправо")
    void calculateNegativeNumberRight() {
        // given
        int value = -28712;

        // when
        int result = Task7.rotateRight(value, 5);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Обработка корректного числа при сдвиге вправо")
    void calculateCorrectNumberRight() {
        // given
        int value = 874;

        // when
        int result = Task7.rotateRight(value, 3);

        // then
        assertThat(result).isEqualTo(365);
    }
}
