package edu.hw1;

public class Task4 {

    private Task4() {
    }

    public static String fixString(String string) throws IllegalArgumentException {
        if (string == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder fixedString = new StringBuilder(string);
        int length = string.length();
        if (length % 2 == 1) {
            length--;
        }
        for (int i = 0; i < length; i += 2) {
            char currChar = fixedString.charAt(i);
            char nextChar = fixedString.charAt(i + 1);
            fixedString.setCharAt(i, nextChar);
            fixedString.setCharAt(i + 1, currChar);
        }

        return fixedString.toString();
    }

}
