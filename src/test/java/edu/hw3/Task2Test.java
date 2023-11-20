package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {
    @Test
    @DisplayName("Обработка правильных кластеров")
    void calculateCorrectClusters() {
        // given
        String clusters = "((()))(())()()(()())";
        List<String> answer = Arrays.asList("((()))", "(())", "()", "()", "(()())");

        // when
        List<String> result = Task2.clusterize(clusters);

        // then
        assertThat(result).isEqualTo(answer);
    }

    @Test
    @DisplayName("Обработка кластеров с другими символами")
    void calculateIncorrectSymbolsCluster() {
        // given
        String clusters = "((()asjdfasd))()()(()())";

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(clusters));
    }

    @Test
    @DisplayName("Обработка кластеров с разным количеством скобок")
    void calculateIncorrectAmountCluster() {
        // given
        String clusters = "()())))";

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(clusters));
    }

    @Test
    @DisplayName("Обработка кластеров с неправильной постановкой скобок")
    void calculateIncorrectPositionCluster() {
        // given
        String clusters = "()())()()(";

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(clusters));
    }

    @Test
    @DisplayName("Обработка пустой строки")
    void calculateEmptyCluster() {
        // given
        String clusters = "";

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(clusters));
    }



}
