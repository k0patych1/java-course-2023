package edu.hw2.task3.connections;

import edu.hw2.task3.exceptions.ConnectionException;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements IConnection {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String MESSAGE_TO_LOGGER_ABOUT_CLOSING = "Connection was closed\n";

    public Random random;

    public FaultyConnection() {
        random = new Random();
    }

    public FaultyConnection(Random random) {
        this.random = random;
    }

    @Override
    public void execute(String command) {
        boolean isWillWork = random.nextBoolean();

        if (!isWillWork) {
            throw new ConnectionException("Failed to execute command on server");
        }

        LOGGER.info("Command \"" + command + "\" was successfully executed\n");
    }

    @Override
    public void close() {
        LOGGER.info(MESSAGE_TO_LOGGER_ABOUT_CLOSING);
    }
}
