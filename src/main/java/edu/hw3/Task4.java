package edu.hw3;


import java.util.NavigableMap;
import java.util.TreeMap;
import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("MagicNumber")
public class Task4 {

    private static final NavigableMap<Integer, String> ROMAN_NUMBERS = new TreeMap<>();

    static {
        ROMAN_NUMBERS.put(1, "I");
        ROMAN_NUMBERS.put(4, "IV");
        ROMAN_NUMBERS.put(5, "V");
        ROMAN_NUMBERS.put(9, "IX");
        ROMAN_NUMBERS.put(10, "X");
        ROMAN_NUMBERS.put(40, "XL");
        ROMAN_NUMBERS.put(50, "L");
        ROMAN_NUMBERS.put(90, "XC");
        ROMAN_NUMBERS.put(100, "C");
        ROMAN_NUMBERS.put(400, "CD");
        ROMAN_NUMBERS.put(500, "D");
        ROMAN_NUMBERS.put(900, "CM");
        ROMAN_NUMBERS.put(1000, "M");
    }

    public static String convertToRoman(int number) throws IllegalArgumentException {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException();
        }
        int floorValue = ROMAN_NUMBERS.floorKey(number);
        if (floorValue == number) {
            return ROMAN_NUMBERS.get(number);
        }
        return ROMAN_NUMBERS.get(floorValue) + convertToRoman(number - floorValue);
    }
}
