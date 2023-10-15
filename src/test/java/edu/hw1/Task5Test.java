package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Обработка одной цифры")
    void calculateDigit() {
        // given
        long value = 1;

        // when
        boolean result = Task5.isPalindromeDescendant(value);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Обработка отрицательного числа")
    void calculateNegativeNumber() {
        // given
        long value = -94652;

        // when
        boolean result = Task5.isPalindromeDescendant(value);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Обработка нечетного не палиндрома")
    void calculateOddNonPalindrom() {
        // given
        long value = 12345;

        // when
        boolean result = Task5.isPalindromeDescendant(value);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Обработка нечетного палиндрома")
    void calculateOddPalindrom() {
        // given
        long value = 3225223;

        // when
        boolean result = Task5.isPalindromeDescendant(value);

        // then
        assertThat(result).isEqualTo(true);
    }

}
