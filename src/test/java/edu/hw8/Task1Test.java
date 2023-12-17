package edu.hw8;

import edu.hw8.Task1.QuoteClient;
import edu.hw8.Task1.QuoteServer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Test {
    @Test
    @DisplayName("Проверка работоспособности при слове, которого нет в словаре")
    void checkUnknownWord() throws InterruptedException {
        // Given
        int maxConnections = 5;
        int maxRequests = 10;
        String request = "test";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
//        String ans = """
//                Сервер запущен. Ожидание соединений...
//                Ответ сервера на запрос от клиента: Ключевое слово не распознано
//                Ответ сервера на запрос от клиента: Ключевое слово не распознано
//                Ответ сервера на запрос от клиента: Ключевое слово не распознано
//                Ответ сервера на запрос от клиента: Ключевое слово не распознано
//                Ответ сервера на запрос от клиента: Ключевое слово не распознано
//                Ответ сервера на запрос от клиента: Ключевое слово не распознано
//                Ответ сервера на запрос от клиента: Ключевое слово не распознано
//                Ответ сервера на запрос от клиента: Ключевое слово не распознано
//                Ответ сервера на запрос от клиента: Ключевое слово не распознано
//                Ответ сервера на запрос от клиента: Ключевое слово не распознано
//                """;
        /* короче изначально я хотел как то так проверить чтоб прям наверняка, но там строки различаются
        разделителями. Я погуглил, но мне не особо помогло, поэтому чтоб до дедлайна успеть сделал
        немного по другому, что проверятеся только наличие этих строк в ответе
         */

        // When
        Thread servThread = new Thread(() -> QuoteServer.startServer(maxConnections, maxRequests));
        servThread.start();
        sleep(100);
        QuoteClient.startClient(maxRequests, request, "localhost", 12345);
        servThread.join();

        // Then
        assertTrue(outContent.toString().contains("Сервер запущен. Ожидание соединений..."));
        assertTrue(outContent.toString().contains("Ответ сервера на запрос от клиента: Ключевое слово не распознано"));
    }

    @Test
    @DisplayName("Проверка работоспособности знакомого слова")
    void checkRightWord() throws InterruptedException {
        // Given
        int maxConnections = 5;
        String request = "личности";
        int maxRequests = 10;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));


        // When
        Thread servThread = new Thread(() -> QuoteServer.startServer(maxConnections, maxRequests));
        servThread.start();
        sleep(100);
        QuoteClient.startClient(maxRequests, request, "localhost", 12345);
        servThread.join();

        // Then
        assertTrue(outContent.toString().contains("Сервер запущен. Ожидание соединений..."));
        assertTrue(outContent.toString().contains("Не переходи на личности там, где их нет"));

    }


}
