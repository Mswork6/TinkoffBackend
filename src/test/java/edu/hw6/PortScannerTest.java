package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PortScannerTest {
    @Test
    @DisplayName("Проверка корректного ответа для одного порта")
    void testOnePort() {
        // given
        String answer = "TCP - In use by EPMAP";
        int port = 135;

        // when
        String result = PortScanner.getPortStatus(port);

        // then
        assertEquals(result, answer);
    }

    @Test
    @DisplayName("Проверка корректного ответа для нескольких портов порта")
    void testSomePorts() {
        // give
        int minPort = 135;
        int maxPort = 140;

        // when
        List<String> result = PortScanner.scanPorts(minPort, maxPort);

        // then
        assertEquals(result.size(), 6);
        /* Я изначально проверял какие порты к чему подключены, но я так понял что идея лажа,
        потому что когда залил на гитхаб там значения другие, поэтому просто проверяем, что все
        порты были просканированы
         */

    }
}
