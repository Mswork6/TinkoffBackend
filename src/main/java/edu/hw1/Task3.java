package edu.hw1;

import java.util.Arrays;

public class Task3 {

    private Task3() {
    }

    private static boolean validationCheck(int[] array1, int[] array2) {
        if (array1 == null || array2 == null) {
            return false;
        }

        return array1.length != 0 && array2.length != 0;
    }

    public static boolean isNestable(int[] array1, int[] array2) {
        if (!validationCheck(array1, array2)) {
            return false;
        }

        int array1Min = Arrays.stream(array1).min().orElseThrow();
        int array1Max = Arrays.stream(array1).max().orElseThrow();
        int array2Min = Arrays.stream(array2).min().orElseThrow();
        int array2Max = Arrays.stream(array2).max().orElseThrow();

        return (array1Min > array2Min) && (array1Max < array2Max);
    }
}
