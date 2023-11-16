package edu.hw3;

import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Task1 {
    private static final Map<Character, Character> ALPHABET_MAP = HashMapInitializer.initialize();

    public static String atbash(String statement) {
        if (statement == null) {
            return "";
        }
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < statement.length(); i++) {
            char curChar = statement.charAt(i);

            if (ALPHABET_MAP.containsKey(curChar)) {
                res.append(ALPHABET_MAP.get(curChar));
            } else {
                res.append(curChar);
            }
        }

        return res.toString();
    }

    private static class HashMapInitializer {
        private static final char FIRST_CAPITAL_LETTER = 'A';
        private static final char LAST_CAPITAL_LETTER = 'Z';
        private static final char FIRST_LOWERCASE_LETTER = 'a';
        private static final char LAST_LOWERCASE_LETTER = 'z';

        private static Map<Character, Character> initialize() {
            Map<Character, Character> aplhabetMap = new HashMap<>();
            int amount = (int) LAST_CAPITAL_LETTER - FIRST_CAPITAL_LETTER + 1;
            for (int i = 0; i < amount; i++) {
                aplhabetMap.put(
                    (char) (FIRST_CAPITAL_LETTER + i),
                    (char) (LAST_CAPITAL_LETTER - i)
                );
                aplhabetMap.put(
                    (char) (FIRST_LOWERCASE_LETTER + i),
                    (char) (LAST_LOWERCASE_LETTER - i)
                );
            }
            return aplhabetMap;
        }
    }
}
