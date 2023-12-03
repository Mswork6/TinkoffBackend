package edu.hw8.Task1;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuoteServer {
    private static final int PORT = 12345;

    @SuppressWarnings("RegexpSinglelineJava")
    public static void startServer(int maxConnections, int maxRequests) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(maxConnections)) {

            Semaphore semaphore = new Semaphore(maxConnections);

            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Сервер запущен. Ожидание соединений...");

                for (int i = 0; i < maxRequests; i++) {
                    Socket clientSocket = serverSocket.accept();
                    executorService.submit(new ClientHandler(clientSocket, semaphore));
                }

                executorService.shutdown();
            } catch (IOException e) {
                System.err.println("Ошибка сервера " + e.getMessage());
            }
        }
    }

}
