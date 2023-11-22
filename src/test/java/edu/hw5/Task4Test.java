package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task4.checkPassword;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    @Test
    @DisplayName("Обработка пароля с нужными символами")
    void checkCorrectPass() {
        // given
        String password = "R3tr0_H@ck3r";

        // when
        boolean answer = checkPassword(password);

        // then
        assertTrue(answer);
    }

    @Test
    @DisplayName("Обработка пароля без нужных символов")
    void checkIncorrectPass() {
        // given
        String password = "Password";

        // when
        boolean answer = checkPassword(password);

        // then
        assertFalse(answer);
    }

    @Test
    @DisplayName("Обработка пустого пароля")
    void checkEmptyPass() {
        // given
        String password = "";

        // when
        boolean answer = checkPassword(password);

        // then
        assertFalse(answer);
    }
}
