package edu.hw3;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Task2 {
    private static class StringValidator {
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
    }

    public static List<String> clusterize(String string) throws IllegalArgumentException {
        if (!StringValidator.validateString(string)) {
            throw new IllegalArgumentException();
        }
        int startIndex = 0;
        Stack<Character> stack = new Stack<>();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < string.length(); i++) {
            char symbol = string.charAt(i);
            switch (symbol) {
                case '(' -> stack.push(symbol);
                case ')' -> {
                    if (stack.get(stack.size() - 1) == '(') stack.pop();
                }
            }
            if (stack.isEmpty()) {
                result.add(string.substring(startIndex, i + 1));
                startIndex = i + 1;
            }
        }
        return result;
    }
}
