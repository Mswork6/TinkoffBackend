package edu.hw8.Task1;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuoteClient {
    public static void startClient(int threadAmount, String address, int port) {
        for (int i = 0; i < threadAmount; i++) {
            new Thread(new QuoteClientTask(i, address, port)).start();
        }
    }

    public static void startClient(int threadAmount, String request, String address, int port) {
        for (int i = 0; i < threadAmount; i++) {
            new Thread(new QuoteClientTask(i, request, address, port)).start();
        }
    }
}

