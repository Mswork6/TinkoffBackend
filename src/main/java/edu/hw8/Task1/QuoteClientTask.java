package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class QuoteClientTask implements Runnable {
    private final static String[] REQUESTS = {"личности", "оскорбления", "глупый", "интеллект"};
    private final static String SERVER_ADDRESS = "localhost";
    private final static int SERVER_PORT = 12345;
    private final static int BUFFER_SIZE = 1024;

    private final int clientId;
    private final String request;

    public QuoteClientTask(int clientId) {
        this.clientId = clientId;
        this.request = REQUESTS[this.clientId % REQUESTS.length];
    }

    public QuoteClientTask(int clientId, String request) {
        this.clientId = clientId;
        this.request = request;

    }

    @SuppressWarnings("RegexpSinglelineJava")
    @Override
    public void run() {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

            // Отправляем запрос на сервер
            OutputStream outputStream = socket.getOutputStream();
            byte[] requestBytes = request.getBytes();
            outputStream.write(requestBytes);

            // Получаем ответ от сервера
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = inputStream.read(buffer);
            String response = new String(buffer, 0, bytesRead);

            System.out.println("Ответ сервера на запрос от клиента: " + response);

            socket.close();
        } catch (IOException e) {
            System.err.println("Произошла ошибка " + e.getMessage());
        }
    }
}
