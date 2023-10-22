package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import edu.project1.GuessResult.Defeat;

public class Project1Test {
    @Test
    @DisplayName("Проверка превышения заданного количества попыток")
    void checkDefeat() {
        // given
        Dictionary dictionary = new GameDictionary();
        ConsoleHangman hangman = new ConsoleHangman(dictionary);

        // when
        hangman.handleCommand("z");
        hangman.handleCommand("z");
        hangman.handleCommand("z");
        hangman.handleCommand("z");
        hangman.handleCommand("z");
        hangman.handleCommand("z");
        hangman.handleCommand("z");
        hangman.handleCommand("z");
        hangman.handleCommand("z");
        var result = hangman.handleCommand("z");

        // then
        assertThat(result).isInstanceOf(Defeat.class);
    }

    @Test
    @DisplayName("Проверка смены состояния игры с неугаданного на угаданное")
    void checkChangeStateOne() {
        // given
        ConsoleHangman hangman = new ConsoleHangman("джава");

        // when
        var resultNotGuessed = hangman.handleCommand("z");
        var resultGuessed = hangman.handleCommand("д");

        // then
        assertThat(resultNotGuessed.getClass()).isNotEqualTo(resultGuessed.getClass());
    }

    @Test
    @DisplayName("Проверка смены состояния игры с угаданного на неугаданное")
    void checkChangeStateTwo() {
        // given
        ConsoleHangman hangman = new ConsoleHangman("джава");

        // when
        var resultNotGuessed = hangman.handleCommand("ж");
        var resultGuessed = hangman.handleCommand("z");

        // then
        assertThat(resultNotGuessed.getClass()).isNotEqualTo(resultGuessed.getClass());
    }

    @Test
    @DisplayName("проверка неизменности состояния при некорректном вводе")
    void checkIncorrectInput() {
        // given
        ConsoleHangman hangman = new ConsoleHangman("джава");

        // when

        // then
        assertThrows(UnsupportedOperationException.class, () -> hangman.handleCommand("дж"));

    }


}
