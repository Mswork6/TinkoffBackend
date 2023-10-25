package edu.hw3;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import edu.hw3.Task8.BackwardIterator;
import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {
    @Test
    @DisplayName("Проверка работоспособности на списке")
    void listTest() {
        // given
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // when
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(list);

        // then
        assertThat(backwardIterator.hasNext()).isTrue();
        assertThat(backwardIterator.next()).isEqualTo(3);

        assertThat(backwardIterator.hasNext()).isTrue();
        assertThat(backwardIterator.next()).isEqualTo(2);

        assertThat(backwardIterator.hasNext()).isTrue();
        assertThat(backwardIterator.next()).isEqualTo(1);

        assertThat(backwardIterator.hasNext()).isFalse();

    }

    @Test
    @DisplayName("Проверка работоспособности на TreeSet")
    void treeSetTest() {
        // given
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(3);
        treeSet.add(1);
        treeSet.add(2);

        // when
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(treeSet);

        // then
        assertThat(backwardIterator.hasNext()).isTrue();
        assertThat(backwardIterator.next()).isEqualTo(3);

        assertThat(backwardIterator.hasNext()).isTrue();
        assertThat(backwardIterator.next()).isEqualTo(2);

        assertThat(backwardIterator.hasNext()).isTrue();
        assertThat(backwardIterator.next()).isEqualTo(1);

        assertThat(backwardIterator.hasNext()).isFalse();

    }

    @Test
    @DisplayName("Проверка работоспособности на TreeMap")
    void hashMapTest() {
        // given
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "three");
        treeMap.put(1, "one");
        treeMap.put(2, "two");

        // when
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(treeMap.keySet());

        // then
        assertThat(backwardIterator.hasNext()).isTrue();
        assertThat(backwardIterator.next()).isEqualTo(3);

        assertThat(backwardIterator.hasNext()).isTrue();
        assertThat(backwardIterator.next()).isEqualTo(2);

        assertThat(backwardIterator.hasNext()).isTrue();
        assertThat(backwardIterator.next()).isEqualTo(1);

        assertThat(backwardIterator.hasNext()).isFalse();

    }

}
