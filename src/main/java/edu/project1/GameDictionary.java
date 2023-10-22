package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class GameDictionary implements Dictionary {
    @Override
    public @NotNull String randomWord() {
        String[] words = {"баг", "фича", "джун", "уронил", "прод", "мидл", "сеньор", "джава"};
        Random random = new Random();
        int index = random.nextInt(words.length);
        return words[index];
    }
}
