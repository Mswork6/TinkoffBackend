package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    @DisplayName("Обработка массива не 8x8")
    void calculateIncorrectLenArray() {
        // given
        int[][] value = {
            {1, 0, 1, 1, 0},
            {1, 1, 0, 0},
            {0, 1, 1}
        };

        // when
        boolean result = Task8.knightBoardCapture(value);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Обработка массива содержащего числа, кроме 0 и 1")
    void calculateIncorrectValuesArray() {
        // given
        int[][] value = {
            {0, 0, 6, 0, 1, 0, 0, 0},
            {0, 0, 2, 0, 0, 1, 0, 0},
            {0, 3, 0, 1, 0, 0, 23, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 12, 0, 1, 0, 0, 0},
            {0, 0, 0, 4, 0, 1, 0, 0},
            {0, 1, 0, 0, 0, 1, 7, 0},
            {1, 0, 2, 8, 0, 0, 0, 0}
        };

        // when
        boolean result = Task8.knightBoardCapture(value);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Обработка корректного массива с положительным результатом")
    void calculateCorrectTrueArray() {
        // given
        int[][] value = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };

        // when
        boolean result = Task8.knightBoardCapture(value);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Обработка корректного массива с отрицательным результатом")
    void calculateCorrectFalseArray() {
        // given
        int[][] value = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };

        // when
        boolean result = Task8.knightBoardCapture(value);

        // then
        assertThat(result).isEqualTo(false);
    }

}
