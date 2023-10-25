package edu.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Task2 {
    private static final Stack<Character> stack = new Stack<>();

    private static final ArrayList<String> result = new ArrayList<>();
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
            stack.push(symbol);
            if (symbol == ')' && stack.get(stack.size() - 2) == '(') {
                stack.pop();
                stack.pop();
            }
            if (stack.isEmpty()) {
                result.add(string.substring(startIndex, i + 1));
                startIndex = i + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<String> ans = clusterize("");
        System.out.println(Arrays.toString(ans.toArray()));
    }

}
