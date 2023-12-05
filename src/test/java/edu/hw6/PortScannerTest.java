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
        assertEquals(result.get(0), "Port 135: TCP - In use by EPMAP");
        assertEquals(result.get(1), "Port 136: TCP/UDP - Available");
        assertEquals(result.get(2), "Port 137: UDP - In use by NetBIOS Name Service");
        assertEquals(result.get(3), "Port 138: UDP - In use by NetBIOS Datagram Service");
        assertEquals(result.get(4), "Port 139: TCP - In use by NetBIOS Session Service");
        assertEquals(result.get(5), "Port 140: TCP/UDP - Available");

    }
}
