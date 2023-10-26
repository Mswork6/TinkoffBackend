package edu.hw3;


import java.util.TreeMap;
import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("MagicNumber")
public class Task4 {

    private static final TreeMap<Integer, String> TREE_MAP = new TreeMap<>();

    static {
        TREE_MAP.put(1, "I");
        TREE_MAP.put(4, "IV");
        TREE_MAP.put(5, "V");
        TREE_MAP.put(9, "IX");
        TREE_MAP.put(10, "X");
        TREE_MAP.put(40, "XL");
        TREE_MAP.put(50, "L");
        TREE_MAP.put(90, "XC");
        TREE_MAP.put(100, "C");
        TREE_MAP.put(400, "CD");
        TREE_MAP.put(500, "D");
        TREE_MAP.put(900, "CM");
        TREE_MAP.put(1000, "M");
    }

    public static String convertToRoman(int number) throws IllegalArgumentException {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException();
        }
        int floorValue = TREE_MAP.floorKey(number);
        if (floorValue == number) {
            return TREE_MAP.get(number);
        }
        return TREE_MAP.get(floorValue) + convertToRoman(number - floorValue);
    }
}
