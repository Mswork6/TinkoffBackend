package edu.project1;

import edu.project1.GuessResult.Defeat;
import edu.project1.GuessResult.FailedGuess;
import edu.project1.GuessResult.SuccessfulGuess;
import edu.project1.GuessResult.Win;

class HangmanSession {
    private final String answer;
    private GuessResult result;

    HangmanSession(String answer, int maxAttempts) {
        this.answer = answer.toLowerCase(); // Приводим слово к нижнему регистру для удобства сравнения
        this.result = new GuessResult.StartState(answer, maxAttempts);
    }

    public void guess(char symbol) {
        char guess = Character.toLowerCase(symbol); // Приводим предполагаемую букву к нижнему регистру
        boolean found = false;

        char[] wordState = result.state();
        int attemptState = result.attempt();

        for (int i = 0; i < answer.length(); i++) {
            if ((answer.charAt(i) == guess && wordState[i] == '*') || wordState[i] == guess) {
                wordState[i] = guess;
                found = true;
            }
        }

        if (isWordGuessed(wordState)) {
            this.result = new Win("Вы выиграли! Загаданное слово: " + answer);
        } else if (found) {
            this.result = new SuccessfulGuess(wordState, attemptState, "Правильно! ");
        } else {
            attemptState--;
            if (attemptState == 0) {
                this.result = new Defeat("Вы проиграли. Загаданное слово: " + answer);
            } else {
                this.result = new FailedGuess(wordState, attemptState, "Неправильно. ");
            }
        }
    }

    public GuessResult getResult() {
        return result;
    }

    public void giveUp() {
        this.result = new Defeat("Вы сдались. Загаданное слово: " + answer);
    }

    private boolean isWordGuessed(char[] answer) {
        for (char c : answer) {
            if (c == '*') {
                return false;
            }
        }
        return true;
    }
}
