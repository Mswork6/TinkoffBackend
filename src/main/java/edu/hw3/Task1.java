package edu.hw3;

import java.util.HashMap;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Task1 {
    private static final HashMap<Character, Character> hashmap = new HashMap<>();

    private static final char FIRST_CAPITAL_LETTER = 'A';
    private static final char LAST_CAPITAL_LETTER = 'Z';
    private static final char FIRST_LOWERCASE_LETTER = 'a';
    private static final char LAST_LOWERCASE_LETTER = 'z';

    static {
        int amount = (int) LAST_CAPITAL_LETTER - FIRST_CAPITAL_LETTER + 1;
        for (int i = 0; i < amount; i++) {
            Task1.hashmap.put(
                (char) (FIRST_CAPITAL_LETTER + i),
                (char) (LAST_CAPITAL_LETTER - i)
            );
            Task1.hashmap.put(
                (char) (FIRST_LOWERCASE_LETTER + i),
                (char) (LAST_LOWERCASE_LETTER - i)
            );
        }
    }
    private static void initHashMap() {

    }

    public static String atbash(String statement) {
        System.out.println(hashmap.toString());
        if (statement == null) {
            return "";
        }

        StringBuilder res = new StringBuilder();
        initHashMap();

        for (int i = 0; i < statement.length(); i++) {
            char curChar = statement.charAt(i);

            if (hashmap.containsKey(curChar)) {
                res.append(hashmap.get(curChar));
            } else {
                res.append(curChar);
            }
        }

        return res.toString();
    }
}
