package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Обработка корректной строки")
    void calculateCorrectStatement() {
        // given
        String statement = "Any fool can write code that a computer can understand. " +
            "Good programmers write code that humans can understand. ― Martin Fowler";

        // when
        String result = Task1.atbash(statement);

        // then
        assertThat(result).isEqualTo("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. " +
            "Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi");
    }

    @Test
    @DisplayName("Обработка строки с не латинскими символами")
    void calculateNonLatinStatement() {
        // given
        String statement = "Эта строка никак не изменится";

        // when
        String result = Task1.atbash(statement);

        // then
        assertThat(result).isEqualTo(statement);
    }

    @Test
    @DisplayName("Обработка пустой строки")
    void calculateEmptyStatement() {
        // given
        String statement = "";

        // when
        String result = Task1.atbash(statement);

        // then
        assertThat(result).isEqualTo(statement);
    }

    @Test
    @DisplayName("обработка null")
    void calculateNull() {
        // given
        String statement = "";

        // when
        String result = Task1.atbash(null);

        // then
        assertThat(result).isEqualTo(statement);
    }
}
