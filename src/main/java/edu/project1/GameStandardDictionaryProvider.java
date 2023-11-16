package edu.project1;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameStandardDictionaryProvider {
    private final static String[] STANDARD_WORDS = {"баг", "фича", "джун", "уронил", "прод", "мидл", "сеньор", "джава"};

    static Dictionary provideStandardDictionary() {
        return new GameDictionary(STANDARD_WORDS);
    }
}
