package edu.hw1;

import java.util.Arrays;

public class Task6 {

    private static final int KAPREKAR_CONSTANT = 6174;
    private static final int INCORRECT_MINIMUM = 1000;
    private static final int INCORRECT_MAXIMUM = 10000;
    private static final int ALL_EQUAL_DIGITS_RADIX = 1111;

    private Task6() {
    }

    private static int[] sort(int value) {
        String stringValue = Integer.toString(value);
        int[] arrValue = new int[stringValue.length()];
        for (int i = 0; i < stringValue.length(); i++) {
            arrValue[i] = Character.getNumericValue(stringValue.charAt(i));
        }
        Arrays.sort(arrValue);
        return arrValue;
    }

    private static int[] reverseSort(int[] array) {
        int[] arrayCopy = Arrays.copyOf(array, array.length);
        int i = 0;
        int j = arrayCopy.length - 1;
        int tmp;
        while (j > i) {
            tmp = arrayCopy[j];
            arrayCopy[j] = arrayCopy[i];
            arrayCopy[i] = tmp;
            j--;
            i++;
        }
        return arrayCopy;
    }

    private static int convertToInt(int[] array) {
        StringBuilder stringValue = new StringBuilder();
        for (int digit : array) {
            stringValue.append(digit);
        }
        return Integer.parseInt(stringValue.toString());
    }

    private static int calculateK(int value) {
        int[] minArray = sort(value);
        int[] maxArray = reverseSort(minArray);
        int minValue = convertToInt(minArray);
        int maxValue = convertToInt(maxArray);
        return maxValue - minValue;
    }

    private static int checkK(int value) {
        if (value == KAPREKAR_CONSTANT) {
            return 0;
        } else {
            int newValue = calculateK(value);
            return (1 + checkK(newValue));
        }
    }

    public static int countK(int value) {
        if (value <= INCORRECT_MINIMUM || value >= INCORRECT_MAXIMUM
            || value % ALL_EQUAL_DIGITS_RADIX == 0) {
            return -1;
        }

        return checkK(value);

    }

}
