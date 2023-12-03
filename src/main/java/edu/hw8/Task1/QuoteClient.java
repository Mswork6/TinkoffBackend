package edu.hw8.Task1;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuoteClient {
    public static void startClient(int threadAmount) {
        for (int i = 0; i < threadAmount; i++) {
            new Thread(new QuoteClientTask(i)).start();
        }
    }

    public static void startClient(int threadAmount, String request) {
        for (int i = 0; i < threadAmount; i++) {
            new Thread(new QuoteClientTask(i, request)).start();
        }
    }
}

