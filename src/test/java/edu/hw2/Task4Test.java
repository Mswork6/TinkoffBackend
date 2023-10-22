package edu.hw2;

import edu.hw2.Task4.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw2.Task4.whoCalled;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Проверка работоспособности метода")
    void processWhoCalled() {
        // given
        CallingInfo res;

        // when
        res = whoCalled();

        // then
        assertThat(res.className()).isEqualTo("edu.hw2.Task4Test");
        assertThat(res.methodName()).isEqualTo("processWhoCalled");
    }

}
