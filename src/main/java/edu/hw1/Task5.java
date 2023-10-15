package edu.hw1;

public class Task5 {

    private Task5() {
    }

    private static boolean checkPalindrom(String number) {
        if (number.length() < 2) {
            return false;
        }

        return number.contentEquals(new StringBuilder(number).reverse());
    }

    private static boolean evenNumber(String number) {
        return number.length() % 2 == 0;
    }

    private static boolean negativeNumber(long number) {
        return number < 0;
    }

    private static String calculateDescendant(String number) {
        StringBuilder currentNumber = new StringBuilder(number);
        StringBuilder newNumber = new StringBuilder();

        for (int i = 0; i < currentNumber.length() - 1; i += 2) {
            int firstDigit = Character.getNumericValue(currentNumber.charAt(i));
            int secondDigit = Character.getNumericValue(currentNumber.charAt(i + 1));
            int result = firstDigit + secondDigit;
            newNumber.append(result);
        }

        return newNumber.toString();
    }

    public static boolean isPalindromeDescendant(long number) {
        if (negativeNumber(number)) {
            return false;
        }

        String stringNum = Long.toString(number);
        boolean isPalindrom;
        boolean isEven;

        do {
            isPalindrom = checkPalindrom(stringNum);
            isEven = evenNumber(stringNum);

            if (isPalindrom) {
                return true;
            } else if (!isEven) {
                return false;
            }
            stringNum = calculateDescendant(stringNum);
        } while (stringNum.length() >= 2);

        return false;
    }
}
