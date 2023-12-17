package edu.hw8.Task1;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import static java.lang.Thread.sleep;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Main {
    private final static int MAX_CONNECTIONS = 5;
    private final static int MAX_REQUESTS = 20;
    private final static int SLEEP_TIME = 1000;

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) throws InterruptedException {
        Thread servThread = new Thread(() -> QuoteServer.startServer(MAX_CONNECTIONS, MAX_REQUESTS));
        Thread clientThread = new Thread(() -> QuoteClient.startClient(MAX_REQUESTS, "localhost", 12345));
        servThread.start();
        sleep(SLEEP_TIME);
        clientThread.start();
        servThread.join();
        clientThread.join();
    }
}
