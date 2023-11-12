package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task6.isSubsequence;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {
    @Test
    @DisplayName("Обработка ситуации, где s - подпоследовательность t")
    void checkCorrectVariant() {
        // given
        String s = "abc";
        String t = "achfdbaabgcaabg";

        // when
        boolean answer = isSubsequence(s, t);

        // then
        assertTrue(answer);
    }

    @Test
    @DisplayName("Обработка ситуации, где s не является подпоследовательностью t")
    void checkCorrectPass() {
        // given
        String s = "abc";
        String t = "wqeruydjsafhalkj";

        // when
        boolean answer = isSubsequence(s, t);

        // then
        assertFalse(answer);
    }
}
