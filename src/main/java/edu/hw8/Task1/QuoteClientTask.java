package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class QuoteClientTask implements Runnable {
    private final static String[] REQUESTS = {"личности", "оскорбления", "глупый", "интеллект"};
    private final static int BUFFER_SIZE = 1024;

    private final int clientId;
    private final String request;
    private final String address;
    private final int port;

    public QuoteClientTask(int clientId, String address, int port) {
        this.clientId = clientId;
        this.address = address;
        this.port = port;
        this.request = REQUESTS[this.clientId % REQUESTS.length];
    }

    public QuoteClientTask(int clientId, String request, String address, int port) {
        this.clientId = clientId;
        this.request = request;
        this.address = address;
        this.port = port;
    }

    @SuppressWarnings("RegexpSinglelineJava")
    @Override
    public void run() {
        try (Socket socket = new Socket(address, port);
             OutputStream outputStream = socket.getOutputStream();
             InputStream inputStream = socket.getInputStream()) {

            // Отправляем запрос на сервер
            byte[] requestBytes = request.getBytes();
            outputStream.write(requestBytes);

            // Получаем ответ от сервера
            StringBuilder responseBuilder = new StringBuilder();
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                responseBuilder.append(new String(buffer, 0, bytesRead, StandardCharsets.UTF_8));
            }

            String response = responseBuilder.toString();
            System.out.println("Ответ сервера на запрос от клиента: " + response);

        } catch (IOException e) {
            System.err.println("Произошла ошибка " + e.getMessage());
        }
    }
}
