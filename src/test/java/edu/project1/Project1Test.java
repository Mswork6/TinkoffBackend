package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import edu.project1.GuessResult.Defeat;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Project1Test {
    @Test
    @DisplayName("Проверка превышения заданного количества попыток")
    void checkDefeat() {
        // given
        Dictionary dictionary = GameStandardDictionaryProvider.provideStandardDictionary();
        HangmanSession hangman = new HangmanSession(dictionary.randomWord(), 3);

        // when

        for (int i = 0; i < 3; i++){
            hangman.guess('z');
        }
        GuessResult result = hangman.getResult();

        // then
        assertThat(result).isInstanceOf(Defeat.class);
    }

    @Test
    @DisplayName("Проверка смены состояния игры с неугаданного на угаданное")
    void checkChangeStateOne() {
        // given
        HangmanSession hangman = new HangmanSession("джава", 10);

        // when
        hangman.guess('z');
        var resultNotGuessed = hangman.getResult();
        hangman.guess('д');
        var resultGuessed = hangman.getResult();

            // then
        assertThat(resultNotGuessed.getClass()).isNotEqualTo(resultGuessed.getClass());
    }

    @Test
    @DisplayName("Проверка смены состояния игры с угаданного на неугаданное")
    void checkChangeStateTwo() {
        // given
        HangmanSession hangman = new HangmanSession("джава", 10);

        // when
        hangman.guess('ж');
        var resultGuessed = hangman.getResult();
        hangman.guess('z');
        var resultNotGuessed = hangman.getResult();

            // then
        assertThat(resultNotGuessed.getClass()).isNotEqualTo(resultGuessed.getClass());
    }

    @Test
    @DisplayName("Проверка функции 'сдаться '")
    void checkGiveUp() {
        // given
        HangmanSession hangman = new HangmanSession("джава", 10);

        // when
        hangman.giveUp();
        GuessResult result = hangman.getResult();

        // then
        assert(result instanceof Defeat);
    }

    @Test
    @DisplayName("Тестирование программы на полное отгадывание слова")
    void checkGuessingWord() throws IOException {
        // given
        HangmanSession hangman = new HangmanSession("джава", 5);
        ConsoleHangman console = new ConsoleHangman(hangman);
        String answer = "д ж а в а";
        byte[] bytes = answer.getBytes(StandardCharsets.UTF_8);
        InputStream in = new ByteArrayInputStream(bytes);
        PrintStream outStream = new PrintStream(System.out);
        in.close();


        // when
        console.run(in, outStream);
        GuessResult result = hangman.getResult();

        // then
        assert(result instanceof GuessResult.Win);
    }



    @Test
    @DisplayName("проверка неизменности состояния при некорректном вводе")
    void checkIncorrectInput() {
        // given
        GameDictionary dictionary = new GameDictionary(new String[]{"джава"});
        HangmanSession session = new HangmanSession(dictionary.randomWord(), 10);
        ConsoleHangman console = new ConsoleHangman(session);

        // when

        // then
        assertThrows(UnsupportedOperationException.class, () -> console.processCommand("дж"));

    }


}
