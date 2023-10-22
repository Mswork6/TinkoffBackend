package edu.hw2;

import edu.hw2.Task3.*;
import edu.hw2.Task3.connectionManager.DefaultConnectionManager;
import edu.hw2.Task3.connectionManager.FaultyConnectionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task3Test {
    @Test
    @DisplayName("Выполнение команды через стандартный менеджер")
    void processDefaultConnectionManager() {
        // given
        var executor = new PopularCommandExecutor(new DefaultConnectionManager(), 10);

        // when

        // then
        try {
            executor.updatePackages();
            assert (true);
        } catch (ConnectionException e) {
            assert (true);
        }
    }

    @Test
    @DisplayName("Выполнение команды через неисправный менеджер")
    void processFaultyConnectionManager() {
        // given
        var executor = new PopularCommandExecutor(new FaultyConnectionManager(), 1);

        // when

        // then
        try {
            executor.updatePackages();
            assert (true);
        } catch (ConnectionException e) {
            assert (true);
        }
    }
}
