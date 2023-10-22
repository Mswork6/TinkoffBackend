package edu.project1;

import edu.project1.GuessResult.Defeat;
import edu.project1.GuessResult.FailedGuess;
import edu.project1.GuessResult.SuccessfulGuess;
import edu.project1.GuessResult.Win;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

class Session {
    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts;

    Session(String answer, int maxAttempts) {
        this.answer = answer.toLowerCase(); // Приводим слово к нижнему регистру для удобства сравнения
        this.userAnswer = new char[answer.length()];
        Arrays.fill(userAnswer, '*');
        this.maxAttempts = maxAttempts;
        this.attempts = maxAttempts;
    }

    @NotNull
    public GuessResult guess(char symbol) {
        char guess = Character.toLowerCase(symbol); // Приводим предполагаемую букву к нижнему регистру
        boolean found = false;

        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == guess && userAnswer[i] == '*') {
                userAnswer[i] = guess;
                found = true;
            }
        }

        if (isWordGuessed()) {
            return new Win("Вы выиграли! Загаданное слово: " + answer);
        } else if (found) {
            return new SuccessfulGuess(userAnswer, attempts, maxAttempts, "Правильно! " + String.valueOf(userAnswer));
        } else {
            attempts--;
            if (attempts == 0) {
                return new Defeat("Вы проиграли. Загаданное слово: " + answer);
            } else {
                return new FailedGuess(userAnswer, attempts, maxAttempts, "Неправильно. " + String.valueOf(userAnswer));
            }
        }
    }

    @NotNull
    public GuessResult giveUp() {
        return new Defeat("Вы сдались. Загаданное слово: " + answer);
    }

    private boolean isWordGuessed() {
        for (char c : userAnswer) {
            if (c == '*') {
                return false;
            }
        }
        return true;
    }
}
