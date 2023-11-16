package edu.project1;

public class Main {
    public static void main(String[] args) {
        Dictionary dictionary = GameStandardDictionaryProvider.provideStandardDictionary();
        int maxAttempts = 10;
        ConsoleHangman consoleHangman = new ConsoleHangman(dictionary, maxAttempts);
        consoleHangman.run();
    }
}
