package edu.hw2.Task3;

import edu.hw2.Task3.connection.Connection;
import edu.hw2.Task3.connectionManager.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;
    private static final Logger LOGGER = LogManager.getLogger();

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        String command = "apt update && apt upgrade -y";
        tryExecute(command);
    }

    void tryExecute(String command) {
        int counter;
        for (counter = 0; counter < maxAttempts; counter++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                break;
            } catch (ConnectionException e) {
                if (counter == maxAttempts - 1) {
                    throw new ConnectionException("Attempt limit exceeded", e);
                }
            } catch (Exception e) {
                LOGGER.error("Unexpected Exception!");
            }
        }
    }
}
