package edu.hw3;


import java.util.HashMap;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Task1 {
    private static final HashMap<Character, Character> HASH_MAP = new HashMap<>();

    private static final int AMOUNT_OF_LETTERS = 26;
    private static final int START_POS_CAPITAL_LETTERS = 65;

    private static final int START_POS_LOWERCASE_LETTERS = 97;
    private static final int DISTANCE = 25;

    private static void initHashMap() {
        for (int i = 0; i < AMOUNT_OF_LETTERS; i++) {
            Task1.HASH_MAP.put(
                (char) (START_POS_CAPITAL_LETTERS + i),
                (char) (START_POS_CAPITAL_LETTERS + DISTANCE - i)
            );
            Task1.HASH_MAP.put(
                (char) (START_POS_LOWERCASE_LETTERS + i),
                (char) (START_POS_LOWERCASE_LETTERS + DISTANCE - i)
            );

        }
    }

    public static String atbash(String statement) {
        if (statement == null) {
            return "";
        }

        StringBuilder res = new StringBuilder();
        initHashMap();

        for (int i = 0; i < statement.length(); i++) {
            char curChar = statement.charAt(i);

            if (HASH_MAP.containsKey(curChar)) {
                res.append(HASH_MAP.get(curChar));
            } else {
                res.append(curChar);
            }
        }

        return res.toString();
    }
}
