package edu.project1;

import edu.project1.GuessResult.Defeat;
import edu.project1.GuessResult.Win;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

class ConsoleHangman {
    private final HangmanSession session;

    private final static String GIVE_UP_STRING = "сдаться";

    ConsoleHangman(Dictionary dictionary, int maxAttempts) {
        this.session = new HangmanSession(dictionary.randomWord(), maxAttempts);
    }

    ConsoleHangman(HangmanSession session) {
        this.session = session;
    }
    /* Конструктор для отладки, чтобы можно было отслеживать изменения в сессии.
    Можно конечно было сделать метод, который возвращал саму сессию отсюда, но мне показалось,
    что так получше будет.
     */

    public void processCommand(String command) throws UnsupportedOperationException {
        if (command.length() != 1 && !command.equalsIgnoreCase(GIVE_UP_STRING)) {
            throw new UnsupportedOperationException();
        } else if (command.equalsIgnoreCase(GIVE_UP_STRING)) {
            session.giveUp();
        }
        char guess = command.charAt(0);
        session.guess(guess);
    }

    public void run(InputStream input, PrintStream output) {
        Scanner scanner = new Scanner(input);
        GameInformer informer = new GameInformer(output);
        GuessResult result = session.getResult();

        informer.info("Добро пожаловать в игру 'Виселица'!");
        informer.info("У вас есть " + result.attempt() + " попыток, чтобы угадать слово.");
        informer.info("Для сдачи введите 'сдаться'");

        while (true) {
            informer.info("Количество попыток: " + result.attempt());
            informer.info("Введите букву: ");

            if (scanner.hasNext()) {
                String token = scanner.next();

                try {
                    processCommand(token);
                    result = session.getResult();
                    informer.info(result);

                    if (result instanceof Defeat || result instanceof Win) {
                        break;
                    }

                } catch (UnsupportedOperationException e) {
                    informer.info("Пожалуйста, введите только одну букву");
                }

            }
        }
    }

    public void run() {
        run(System.in, System.out);
    }
    /* Сделал метод с перегрузкой чтобы можно было работать с разными потоками ввода - вывода,
    чем собственно и воспользовался в тестах :-)
     */

    private record GameInformer(PrintStream output) {

        @SuppressWarnings("RegexpSinglelineJava")
            public void info(String message) {
                output.println(message);
            }

            public void info(GuessResult guess) {
                if (guess instanceof Defeat || guess instanceof Win) {
                    info(guess.message());
                } else {
                    info(guess.message() + Arrays.toString(guess.state()));
                }
            }
        }
}
