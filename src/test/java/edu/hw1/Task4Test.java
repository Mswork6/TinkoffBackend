package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task4Test {

    @Test
    @DisplayName("Обработка строки с нечётным количеством символов")
    void processOddString() {
        // given
        String string = "uahtrozitaoin";

        // when
        String result = Task4.fixString(string);

        // then
        assertThat(result).isEqualTo("authorization");
    }

    @Test
    @DisplayName("Обработка строки с чётным количеством символов")
    void processEvenString() {
        // given
        String string = "oggoel";

        // when
        String result = Task4.fixString(string);

        // then
        assertThat(result).isEqualTo("google");
    }

    @Test
    @DisplayName("Обработка пустой строки")
    void processEmptyString() {
        // given
        String string = "";

        // when
        String result = Task4.fixString(string);

        // then
        assertThat(result).isEqualTo("");
    }

    @Test
    @DisplayName("Обработка null")
    void processNullString() {
        // given

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> Task4.fixString(null));
    }

}
