package edu.hw1;

public final class Task2 {

    private final static int DECIMAL_RADIX = 10;

    private Task2() {
    }

    public static int countDigits(int value) {

        int valueCopy = value;
        int result = 0;
        do {
            result++;
            valueCopy /= DECIMAL_RADIX;
        } while (valueCopy != 0);
        return result;
    }
}
