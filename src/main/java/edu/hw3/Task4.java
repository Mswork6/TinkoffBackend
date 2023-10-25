package edu.hw3;

import java.util.TreeMap;

public class Task4 {
    private static final TreeMap<Integer, String> treeMap = new TreeMap<>();

    static {
        treeMap.put(1, "I");
        treeMap.put(4, "IV");
        treeMap.put(5, "V");
        treeMap.put(9, "IX");
        treeMap.put(10, "X");
        treeMap.put(40, "XL");
        treeMap.put(50, "L");
        treeMap.put(90, "XC");
        treeMap.put(100, "C");
        treeMap.put(400, "CD");
        treeMap.put(500, "D");
        treeMap.put(900, "CM");
        treeMap.put(1000, "M");
    }

    public static String convertToRoman(int number) throws IllegalArgumentException{
        if (number < 1 || number > 3999){
            throw new IllegalArgumentException();
        }
        int floorValue = treeMap.floorKey(number);
        if (floorValue == number){
            return treeMap.get(number);
        }
        return treeMap.get(floorValue) + convertToRoman(number - floorValue);
    }

    public static void main(String[] args) {
        System.out.println(convertToRoman(23));
    }
}
