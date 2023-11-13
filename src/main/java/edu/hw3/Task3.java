package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Task3 {

    public static <T> Map<T, Integer> freqDict(List<T> arrayList) {
        Map<T, Integer> hashMap = new HashMap<>();

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
