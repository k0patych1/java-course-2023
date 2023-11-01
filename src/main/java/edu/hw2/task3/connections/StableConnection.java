package edu.hw2.task3.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements IConnection {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String MESSAGE_TO_LOGGER_ABOUT_CLOSING = "Connection was closed\n";

    @Override
    public void execute(String command) {
        LOGGER.info("Command \"" + command + "\" was successfully executed\n");
    }

    @Override
    public void close() {
        LOGGER.info(MESSAGE_TO_LOGGER_ABOUT_CLOSING);
    }
}
