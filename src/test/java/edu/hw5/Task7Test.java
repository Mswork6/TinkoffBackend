package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task7.checkAllRegexes;
import static edu.hw5.Task7.checkFirstRegex;
import static edu.hw5.Task7.checkSecondRegex;
import static edu.hw5.Task7.checkThirdRegex;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task7Test {
    @Test
    @DisplayName("Проверка работы первого регулярного выражения")
    void checkRegexWork1() {
        // given
        String correctString = "1101110";
        String incorrectString = "01";
        String incorrectString2 = "001";


        // when
        boolean correctAnswer = checkFirstRegex(correctString);
        boolean incorrectAnswer = checkFirstRegex(incorrectString);
        boolean incorrectAnswer2 = checkFirstRegex(incorrectString2);

        // then
        assertTrue(correctAnswer);
        assertFalse(incorrectAnswer);
        assertFalse(incorrectAnswer2);
    }

    @Test
    @DisplayName("Проверка работы второго регулярного выражения")
    void checkRegexWork2() {
        // given
        String correctString = "1101111";
        String incorrectString = "01";


        // when
        boolean correctAnswer = checkSecondRegex(correctString);
        boolean incorrectAnswer = checkSecondRegex(incorrectString);

        // then
        assertTrue(correctAnswer);
        assertFalse(incorrectAnswer);
    }

    @Test
    @DisplayName("Проверка работы третьего регулярного выражения")
    void checkRegexWork3() {
        // given
        String correctString = "101";
        String incorrectString = "10010";


        // when
        boolean correctAnswer = checkThirdRegex(correctString);
        boolean incorrectAnswer = checkThirdRegex(incorrectString);

        // then
        assertTrue(correctAnswer);
        assertFalse(incorrectAnswer);
    }

    @Test
    @DisplayName("Проверка работы всех регулярных выражений")
    void checkAllRegexWork() {
        // given
        String correctString = "010";
        String correctString2 = "000";
        String incorrectString = "101";


        // when
        boolean correctAnswer = checkAllRegexes(correctString);
        boolean correctAnswer2 = checkAllRegexes(correctString2);
        boolean incorrectAnswer = checkAllRegexes(incorrectString);

        // then
        assertTrue(correctAnswer);
        assertTrue(correctAnswer2);
        assertFalse(incorrectAnswer);
    }
}
