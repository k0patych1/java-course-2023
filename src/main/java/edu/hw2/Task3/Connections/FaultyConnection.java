package edu.hw2.Task3.Connections;

import edu.hw2.Task3.Exceptions.ConnectionException;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements IConnection {
    private static final Logger LOGGER = LogManager.getLogger();

    private static String loggerInformation = "";

    private static final String MESSAGE_TO_LOGGER_ABOUT_CLOSING = "Connection was closed\n";

    @Override
    public void execute(String command) {
        Random random = new Random();
        boolean isWillWork = random.nextBoolean();

        if (!isWillWork) {
            throw new ConnectionException("Failed to execute command on server");
        }

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
