package edu.hw2.Task3.connection;

import edu.hw2.Task3.ConnectionException;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();
    private final Random random = new Random();
    private static final int MAXIMUM = 101;
    private static final int MINIMUM = 1;
    private static final int PERCENTAGE = 90;

    @Override
    public void execute(String command) throws ConnectionException {
        if (random.nextInt(MINIMUM, MAXIMUM) <= PERCENTAGE) {
            throw new ConnectionException("Connection error");
        }
        LOGGER.info(command);
    }

    @Override
    public void close() {
    }
}
