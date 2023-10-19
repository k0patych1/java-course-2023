package edu.hw2.Task3;

import edu.hw2.Task3.ConnectionManagers.IConnectionManager;
import edu.hw2.Task3.Connections.IConnection;
import edu.hw2.Task3.Exceptions.ConnectionException;
import edu.hw2.Task3.Exceptions.ExceedingConnectionAttemptsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private final IConnectionManager manager;

    private static String loggerInformation = "";

    private final int maxAttempts;

    private final static Logger LOGGER = LogManager.getLogger();

    public void updatePackages() throws ExceedingConnectionAttemptsException {
        tryExecute("apt update && apt upgrade -y");
    }

    public PopularCommandExecutor(IConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    void tryExecute(String command) throws ExceedingConnectionAttemptsException {
        int attempts = 0;
        IConnection curConnection = manager.getConnection();

        while (true) {
            try {
                curConnection.execute(command);
            } catch (ConnectionException exception) {
                ++attempts;
                if (attempts >= maxAttempts) {
                    curConnection.close();
                    loggerInformation += curConnection.getLoggerInformation();
                    throw new ExceedingConnectionAttemptsException(
                            "The number of attempts to execute a command has been exceeded",
                            exception);
                }

                continue;
            }

            curConnection.close();
            loggerInformation += curConnection.getLoggerInformation();

            break;
        }
    }

    public String getLoggerInformation() {
        return loggerInformation;
    }

}
