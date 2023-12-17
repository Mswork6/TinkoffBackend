package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import static java.lang.Thread.sleep;

class ClientHandler implements Runnable {
    private final static int BUFFER_SIZE = 1024;
    private final static int SLEEP_TIME = 2000;
    private final static Map<String, String> RESPONSE_MAP = new HashMap<>();

    static {
        RESPONSE_MAP.put("личности", "Не переходи на личности там, где их нет");
        RESPONSE_MAP.put("оскорбления", "Если твои противники перешли на личные оскорбления, будь уверен — твоя победа не за горами");
        RESPONSE_MAP.put("глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.");
        RESPONSE_MAP.put("интеллект", "Чем ниже интеллект, тем громче оскорбления");
    }

    private final Socket clientSocket;

    ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            handleClient();
        } catch (IOException e) {
            System.err.println("Ошибка при обработке данных: " + e.getMessage());
        }
    }

    private void handleClient() throws IOException {
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = inputStream.read(buffer);
            String clientMessage = new String(buffer, 0, bytesRead);

            String response = getResponse(clientMessage);
            byte[] responseBytes = response.getBytes();
            outputStream.write(responseBytes);

            try {
                sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        } finally {
            clientSocket.close();
        }
    }

    private String getResponse(String clientMessage) {
        for (Map.Entry<String, String> entry : RESPONSE_MAP.entrySet()) {
            if (clientMessage.toLowerCase().contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "Ключевое слово не распознано";
    }
}
