package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task1.getTimeDifference;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Проверка корректных входных данных")
    void calculateCorrectValue() {
        // given
        String[] time = {"2022-03-12, 20:20 - 2022-03-12, 23:50",
        "2022-04-01, 21:30 - 2022-04-02, 01:20"};

        // when
        String result = getTimeDifference(time);

        // then
        assertThat(result).isEqualTo("3ч 40м");
    }

    @Test
    @DisplayName("Проверка частично некорректных входных данных")
    void calculateIncorrectValue() {
        // given
        String[] time = {"2022-03-12, 20:20 - 2022-03-12, 23:50",
            "Некорректная строка появилась из ниоткуда",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"};

        // when
        String result = getTimeDifference(time);

        // then
        assertThat(result).isEqualTo("3ч 40м");
    }

    @Test
    @DisplayName("Проверка полностью некорректных входных данных")
    void calculateFullyIncorrectValue() {
        // given
        String[] time = {"2022-03-12,20:20 - 2022-03-12, 23:50",
            "Некорректная строка появилась из ниоткуда",
            "2022-04-01 21:30 - 2022-04-02 01:20"};

        // when
        String result = getTimeDifference(time);
        System.out.println(result);

        // then
        assertThat(result).isEqualTo("0ч 0м");
    }

    @Test
    @DisplayName("Обработка ситуации, когда даты в строке написаны в обратном порядке")
    void calculateReverseValue() {
        // given
        String[] time = {"2022-03-12, 23:50 - 2022-03-12, 20:20",
            "2022-04-02, 01:20 - 2022-04-01, 21:30"};

        // when
        String result = getTimeDifference(time);

        // then
        assertThat(result).isEqualTo("3ч 40м");
    }
}
