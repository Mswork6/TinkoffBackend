package edu.project1;

import edu.project1.GuessResult.Defeat;
import edu.project1.GuessResult.StartState;
import edu.project1.GuessResult.Win;
import java.util.Scanner;

class ConsoleHangman {
    private static final int MAX_ATTEMPTS = 10;
    private GuessResult result;
    private final Session session;

    ConsoleHangman(Dictionary dictionary) {
        this.session = new Session(dictionary.randomWord(), MAX_ATTEMPTS);
        this.result = new StartState(MAX_ATTEMPTS);
    }

    ConsoleHangman(String answer) {
        this.session = new Session(answer, MAX_ATTEMPTS);
        this.result = new StartState(MAX_ATTEMPTS);
    }

    @SuppressWarnings("RegexpSinglelineJava") private static void gameLog(String message) {
        System.out.println(message);
    }

    private GuessResult processCommand(String command) throws UnsupportedOperationException {
        if (command.equalsIgnoreCase("сдаться")) {
            return session.giveUp();
        }
        if (command.length() != 1) {
            throw new UnsupportedOperationException();
        }
        char guess = command.charAt(0);
        return tryGuess(guess);
    }

    public GuessResult handleCommand(String command) throws UnsupportedOperationException {
        result = processCommand(command);
        return result;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        gameLog("Добро пожаловать в игру 'Виселица'!");
        gameLog("У вас есть " + MAX_ATTEMPTS + " попыток, чтобы угадать слово.");
        gameLog("Для сдачи введите 'сдаться'");

        while (true) {
            gameLog("Количество попыток: " + result.attempt());
            gameLog("Введите букву: ");

            if (scanner.hasNext()) {
                String input = scanner.next();

                try {
                    handleCommand(input);
                    printState(result);

                    if (result instanceof Defeat || result instanceof Win) {
                        break;
                    }

                } catch (UnsupportedOperationException e) {
                    gameLog("Пожалуйста, введите только одну букву");
                }

            }
        }
    }

    private GuessResult tryGuess(char guess) {
        return session.guess(guess);
    }

    private void printState(GuessResult guess) {
        gameLog(guess.message());
    }
}
