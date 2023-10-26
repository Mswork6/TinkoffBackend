package edu.hw3;


import java.util.ArrayList;
import java.util.Stack;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Task2 {
    private static final Stack<Character> STACK = new Stack<>();

    private static final ArrayList<String> RESULT = new ArrayList<>();
    private static int leftBracketCounter = 0;
    private static int rightBracketCounter = 0;

    private static boolean checkSymbol(char symbol) {
        switch (symbol) {
            case '(' -> leftBracketCounter++;
            case ')' -> {
                if (rightBracketCounter >= leftBracketCounter) {
                    return false;
                }
                rightBracketCounter++;
            }
            default -> {
                return false;
            }
        }
        return true;
    }

    private static boolean validateString(String string) {
        if (string.isEmpty()) {
            return false;
        }
        for (int i = 0; i < string.length(); i++) {
            char symbol = string.charAt(i);
            if (!checkSymbol(symbol)) {
                return false;
            }
        }
        return leftBracketCounter == rightBracketCounter;
    }

    public static ArrayList<String> clusterize(String string) throws IllegalArgumentException {
        int startIndex = 0;
        if (!validateString(string)) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < string.length(); i++) {
            char symbol = string.charAt(i);
            STACK.push(symbol);
            if (symbol == ')' && STACK.get(STACK.size() - 2) == '(') {
                STACK.pop();
                STACK.pop();
            }
            if (STACK.isEmpty()) {
                RESULT.add(string.substring(startIndex, i + 1));
                startIndex = i + 1;
            }
        }
        return RESULT;
    }
}
