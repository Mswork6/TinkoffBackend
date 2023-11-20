package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class GameDictionary implements Dictionary {
    private final String[] words;

    public GameDictionary(String[] words) {
        this.words = words;
    }

    @Override
    public @NotNull String randomWord() {
        Random random = new Random();
        int index = random.nextInt(words.length);
        return words[index];
    }
}
