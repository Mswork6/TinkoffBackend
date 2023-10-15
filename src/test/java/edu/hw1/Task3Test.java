package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Обработка ситуации, когда минимальный элемент первого массива" +
        "меньше минимального элемента второго массива")
    void calculateMinIncorrectValue() {
        // given
        int[] array1 = {1, 2, 3, 5, 6};
        int[] array2 = {7, 8, 10, 12, 13};

        // when
        boolean result = Task3.isNestable(array1, array2);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Обработка ситуации, когда максимальный элемент первого массива" +
        "больше маскимального элемента второго массива")
    void calculateMaxIncorrectValue() {
        // given
        int[] array1 = {2, 3, 7, 8, 9};
        int[] array2 = {4, 5, 6, 7};

        // when
        boolean result = Task3.isNestable(array1, array2);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Обработка ситуации, когда один из массивов равен null")
    void calculateNullValue() {
        // given
        int[] array1 = {1, 2, 3, 5, 6};
        int[] array2 = null;

        // when
        boolean result = Task3.isNestable(array1, array2);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Обработка ситуации, когда один из массивов пуст")
    void calculateEmptyValue() {
        // given
        int[] array1 = {};
        int[] array2 = {2, 4, 5, 7, 9, 12};

        // when
        boolean result = Task3.isNestable(array1, array2);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Обработка ситуации, когда первый массив может" +
        "быть вложен во второй")
    void calculateCorrectValue() {
        // given
        int[] array1 = {5, 7, 8};
        int[] array2 = {1, 2, 4, 9, 12, 15};

        // when
        boolean result = Task3.isNestable(array1, array2);

        // then
        assertThat(result).isEqualTo(true);
    }
}
