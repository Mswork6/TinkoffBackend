package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Создание частотного словаря строк")
    void calculateStringDict() {
        // given
        ArrayList<String> array = new ArrayList<>();
        array.add("string");
        array.add("this");
        array.add("a");
        array.add("string");
        array.add("string");
        array.add("string");
        array.add("a");
        array.add("this");
        array.add("this");


        HashMap<String, Integer> answer = new HashMap<>();
        answer.put("string", 4);
        answer.put("this", 3);
        answer.put("a", 2);

        // when
        HashMap<String, Integer> result = Task3.freqDict(array);

        // then
        assertThat(result).isEqualTo(answer);
    }

    @Test
    @DisplayName("Создание частотного словаря чисел")
    void calculateIntegerDict() {
        // given
        ArrayList<Integer> array = new ArrayList<>();
        array.add(4);
        array.add(4);
        array.add(4);
        array.add(5);
        array.add(5);
        array.add(5);
        array.add(5);
        array.add(5);
        array.add(5);

        HashMap<Integer, Integer> answer = new HashMap<>();
        answer.put(5, 6);
        answer.put(4, 3);


        // when
        HashMap<Integer, Integer> result = Task3.freqDict(array);

        // then
        assertThat(result).isEqualTo(answer);
    }

    @Test
    @DisplayName("Создание частотного словаря дробных чисел")
    void calculateDoubleDict() {
        // given
        ArrayList<Double> array = new ArrayList<>();
        array.add(34.23);
        array.add(23.45);
        array.add(23.45);
        array.add(23.45);
        array.add(23.45);

        HashMap<Double, Integer> answer = new HashMap<>();
        answer.put(34.23, 1);
        answer.put(23.45, 4);

        // when
        HashMap<Double, Integer> result = Task3.freqDict(array);

        // then
        assertThat(result).isEqualTo(answer);
    }



}
