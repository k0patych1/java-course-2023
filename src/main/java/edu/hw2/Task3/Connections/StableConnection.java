package edu.hw2.Task3.Connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements IConnection {
    private static final Logger LOGGER = LogManager.getLogger();

    private static String loggerInformation = "";

    private static final String MESSAGE_TO_LOGGER_ABOUT_CLOSING = "Connection was closed\n";

    @Override
    public void execute(String command) {
        String out = "Command \"" + command + "\" was successfully executed\n";
        LOGGER.info(out);
        loggerInformation += out;
    }

    @Override
    public void close() {
        LOGGER.info(MESSAGE_TO_LOGGER_ABOUT_CLOSING);
        loggerInformation += MESSAGE_TO_LOGGER_ABOUT_CLOSING;
    }

    @Override
    public String getLoggerInformation() {
        return loggerInformation;
    }
}
