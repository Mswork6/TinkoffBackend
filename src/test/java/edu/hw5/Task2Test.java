package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static edu.hw5.Task2.findFridaysOnThe13th;
import static edu.hw5.Task2.findNextFridayThe13;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Проверка корректной работы нахождения пятниц 13 в заданном году")
    void checkCorrectWorkFindFridays() {
        // given
        int year = 1925;
        List<LocalDate> answer = new ArrayList<>();
        answer.add(LocalDate.parse("1925-02-13"));
        answer.add(LocalDate.parse("1925-03-13"));
        answer.add(LocalDate.parse("1925-11-13"));

        // when
        List<LocalDate> result = findFridaysOnThe13th(year);

        // then
        assertThat(result).isEqualTo(answer);
    }

    @Test
    @DisplayName("Проверка корректной работы нахождения следующей пятницы 13")
    void checkCorrectWorkNextFriday() {
        // given
        LocalDate date = LocalDate.parse("1925-02-13");

        LocalDate answer = LocalDate.parse("1925-03-13");

        // when
        LocalDate result = findNextFridayThe13(date);

        // then
        assertThat(result).isEqualTo(answer);
    }
}
