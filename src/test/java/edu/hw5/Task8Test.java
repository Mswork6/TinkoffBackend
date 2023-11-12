package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task8.checkRegex1;
import static edu.hw5.Task8.checkRegex2;
import static edu.hw5.Task8.checkRegex3;
import static edu.hw5.Task8.checkRegex4;
import static edu.hw5.Task8.checkRegex5;
import static edu.hw5.Task8.checkRegex7;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task8Test {
    @Test
    @DisplayName("Проверка работы первого регулярного выражения")
    void checkRegexWork1() {
        // given
        String correctString = "10101";
        String incorrectString = "1111";

        // when
        boolean correctAnswer = checkRegex1(correctString);
        boolean incorrectAnswer = checkRegex1(incorrectString);

        // then
        assertTrue(correctAnswer);
        assertFalse(incorrectAnswer);
    }

    @Test
    @DisplayName("Проверка работы второго регулярного выражения")
    void checkRegexWork2() {
        // given
        String correctString1 = "01100";
        String incorrectString1 = "0000";
        String correctString2 = "1001";
        String incorrectString2 = "100";

        // when
        boolean correctAnswer1 = checkRegex2(correctString1);
        boolean incorrectAnswer1 = checkRegex2(incorrectString1);
        boolean correctAnswer2 = checkRegex2(correctString2);
        boolean incorrectAnswer2 = checkRegex2(incorrectString2);

        // then
        assertTrue(correctAnswer1);
        assertFalse(incorrectAnswer1);
        assertTrue(correctAnswer2);
        assertFalse(incorrectAnswer2);
    }

    @Test
    @DisplayName("Проверка работы третьего регулярного выражения")
    void checkRegexWork3() {
        // given
        String correctString = "1000111011100100011";
        String incorrectString = "11111001100011";
        // when
        boolean correctAnswer = checkRegex3(correctString);
        boolean incorrectAnswer = checkRegex3(incorrectString);

        // then
        assertTrue(correctAnswer);
        assertFalse(incorrectAnswer);
    }

    @Test
    @DisplayName("Проверка работы четвертого регулярного выражения")
    void checkRegexWork4() {
        // given
        String correctString = "11000101001001010";
        String correctString2 = "111010101";
        String incorrectString = "111";
        String incorrectString2 = "11";

        // when
        boolean correctAnswer = checkRegex4(correctString);
        boolean correctAnswer2 = checkRegex4(correctString2);
        boolean incorrectAnswer = checkRegex4(incorrectString);
        boolean incorrectAnswer2 = checkRegex4(incorrectString2);

        // then
        assertTrue(correctAnswer);
        assertTrue(correctAnswer2);
        assertFalse(incorrectAnswer);
        assertFalse(incorrectAnswer2);
    }

    @Test
    @DisplayName("Проверка работы пятого регулярного выражения")
    void checkRegexWork5() {
        // given
        String correctString = "101110111010";
        String incorrectString = "0110111011";

        // when
        boolean correctAnswer = checkRegex5(correctString);
        boolean incorrectAnswer = checkRegex5(incorrectString);

        // then
        assertTrue(correctAnswer);
        assertFalse(incorrectAnswer);
    }

    @Test
    @DisplayName("Проверка работы cедьмого регулярного выражения")
    void checkRegexWork7() {
        // given
        String correctString = "1000010000010101";
        String incorrectString = "000110010010001";

        // when
        boolean correctAnswer = checkRegex7(correctString);
        boolean incorrectAnswer = checkRegex7(incorrectString);

        // then
        assertTrue(correctAnswer);
        assertFalse(incorrectAnswer);
    }
}
