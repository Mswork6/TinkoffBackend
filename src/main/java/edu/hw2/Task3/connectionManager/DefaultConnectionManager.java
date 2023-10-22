package edu.hw2.Task3.connectionManager;

import edu.hw2.Task3.connection.Connection;
import edu.hw2.Task3.connection.FaultyConnection;
import edu.hw2.Task3.connection.StableConnection;
import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private final Random random = new Random();
    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 101;
    private static final int PERCENTAGE = 40;

    @Override
    public Connection getConnection() {
        if (random.nextInt(MINIMUM, MAXIMUM) <= PERCENTAGE) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
