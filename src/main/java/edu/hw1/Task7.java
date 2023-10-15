package edu.hw1;

public class Task7 {

    private Task7() {
    }

    private static int[] toArray(String value) {
        int[] array = new int[value.length()];
        for (int i = 0; i < value.length(); i++) {
            array[i] = Character.getNumericValue(value.charAt(i));
        }
        return array;
    }

    private static int convertToInt(int[] array) {
        StringBuilder stringValue = new StringBuilder();
        for (int digit : array) {
            stringValue.append(digit);
        }
        return Integer.parseInt(stringValue.toString(), 2);
    }

    public static int rotateLeft(int n, int shift) {
        if (n < 0) {
            return -1;
        }

        String binaryNumber = Integer.toBinaryString(n);
        int len = binaryNumber.length();
        int[] binaryArray = toArray(binaryNumber);
        int[] newBinaryArray = new int[len];
        int shiftValue = shift % len;

        for (int i = 0; i < len; i++) {
            newBinaryArray[(len + (i - shiftValue)) % len] = binaryArray[i];
        }

        return convertToInt(newBinaryArray);
    }

    public static int rotateRight(int n, int shift) {
        if (n < 0) {
            return -1;
        }

        String binaryNumber = Integer.toBinaryString(n);
        int len = binaryNumber.length();
        int[] binaryArray = toArray(binaryNumber);
        int[] newBinaryArray = new int[len];
        int shiftValue = shift % len;

        for (int i = 0; i < len; i++) {
            newBinaryArray[(i + shiftValue) % len] = binaryArray[i];
        }

        return convertToInt(newBinaryArray);
    }

}
