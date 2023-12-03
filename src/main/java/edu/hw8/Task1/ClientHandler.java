package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Semaphore;
import static java.lang.Thread.sleep;

class ClientHandler implements Runnable {
    private final static int BUFFER_SIZE = 1024;
    private final static int SLEEP_TIME = 2000;
    private final static String[] KEYWORDS = {"личности", "оскорбления", "глупый", "интеллект"};
    private final static String[] RESPONSES = {
        "Не переходи на личности там, где их нет",
        "Если твои противники перешли на личные оскорбления, будь уверен — твоя победа не за горами",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "Чем ниже интеллект, тем громче оскорбления"
    };

    private final Socket clientSocket;
    private final Semaphore semaphore;

    ClientHandler(Socket clientSocket, Semaphore semaphore) {
        this.clientSocket = clientSocket;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            handleClient();
        } catch (IOException | InterruptedException e) {
            System.err.println("Ошибка при обработке данных: " + e.getMessage());
        } finally {
            semaphore.release();
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
        for (int i = 0; i < KEYWORDS.length; i++) {
            if (clientMessage.toLowerCase().contains(KEYWORDS[i])) {
                return RESPONSES[i];
            }
        }
        return "Ключевое слово не распознано";
    }
}
