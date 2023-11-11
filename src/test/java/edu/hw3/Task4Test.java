package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task4Test {
    @Test
    @DisplayName("Обработка минимального значения")
    void calculateMinimumNumber() {
        // given
        int number = 1;

        // when
        String result = Task4.convertToRoman(number);

        // then
        assertThat(result).isEqualTo("I");
    }

    @Test
    @DisplayName("Обработка максимального значения")
    void calculateMaximumNumber() {
        // given
        int number = 3999;

        // when
        String result = Task4.convertToRoman(number);

        // then
        assertThat(result).isEqualTo("MMMCMXCIX");
    }

    @ParameterizedTest
    @CsvSource({"87,LXXXVII", "385,CCCLXXXV", "977,CMLXXVII", "1567,MDLXVII"})
    @DisplayName("Обработка различных значений")
    void calculateDifferentNumbers(int number, String answer) {
        // given

        // when
        String result = Task4.convertToRoman(number);

        // then
        assertThat(result).isEqualTo(answer);
    }


    @Test
    @DisplayName("Обработка значения меньше минимального")
    void calculateIncorrectMinimumNumber() {
        // given
        int number = -5;

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(number));
    }

    @Test
    @DisplayName("Обработка значения больше максимального")
    void calculateIncorrectMaximumNumber() {
        // given
        int number = 10000;

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(number));
    }
}
