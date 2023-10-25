package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task7Test {
    @Test
    @DisplayName("Проверка добавления null")
    void addNullTest() {
        // given
        TreeMap<String, String> tree = new TreeMap<>(new Task7.NullComparator());

        // when
        tree.put(null, "test");

        // then
        assertThat(tree.containsKey(null)).isTrue();
    }
}
