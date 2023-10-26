package edu.hw3;


import java.util.ArrayList;
import java.util.HashMap;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Task3 {

    public static <T> HashMap<T, Integer> freqDict(ArrayList<T> arrayList) {
        HashMap<T, Integer> hashMap = new HashMap<>();

        for (T obj : arrayList) {
            if (hashMap.containsKey(obj)) {
                hashMap.put(obj, hashMap.get(obj) + 1);
            } else {
                hashMap.put(obj, 1);
            }
        }

        return hashMap;
    }
}
