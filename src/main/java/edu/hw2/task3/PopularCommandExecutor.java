package edu.hw2.task3;

import edu.hw2.task3.connectionManagers.IConnectionManager;
import edu.hw2.task3.connections.IConnection;
import edu.hw2.task3.exceptions.ConnectionException;
import edu.hw2.task3.exceptions.ExceedingConnectionAttemptsException;

public final class PopularCommandExecutor {
    private final IConnectionManager manager;

    private final int maxAttempts;

    public void updatePackages() throws ExceedingConnectionAttemptsException {
        tryExecute("apt update && apt upgrade -y");
    }

    public PopularCommandExecutor(IConnectionManager manager, int maxAttempts) {
        if (maxAttempts < 1) {
            throw new IllegalArgumentException("Max attempts can't be less than 1");
        }

        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    void tryExecute(String command) throws ExceedingConnectionAttemptsException {
        int attempts = 0;

        while (true) {
            try (IConnection curConnection = manager.getConnection()) {
                curConnection.execute(command);
            } catch (ConnectionException exception) {
                ++attempts;
                if (attempts >= maxAttempts) {
                    throw new ExceedingConnectionAttemptsException(
                            "The number of attempts to execute a command has been exceeded",
                            exception);
                }

                continue;
            }

            break;
        }
    }
}
