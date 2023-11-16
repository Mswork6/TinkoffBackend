package edu.project1;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Main {
    private static final int MAX_ATTEMPTS = 10;

    public static void main(String[] args) {
        Dictionary dictionary = GameStandardDictionaryProvider.provideStandardDictionary();
        ConsoleHangman consoleHangman = new ConsoleHangman(dictionary, MAX_ATTEMPTS);
        consoleHangman.run();
    }
}
