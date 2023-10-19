package edu.hw2.Task3.ConnectionManagers;

import edu.hw2.Task3.Connections.FaultyConnection;
import edu.hw2.Task3.Connections.IConnection;
import edu.hw2.Task3.Connections.StableConnection;
import java.util.Random;

public class DefaultConnectionManager implements IConnectionManager {
    private IConnection connection;

    public DefaultConnectionManager() {
        createConnection();
    }

    public IConnection createConnection() {
        Random random = new Random();
        boolean isStableIConnection = random.nextBoolean();

        if (isStableIConnection) {
            connection =  new StableConnection();
        } else {
            connection =  new FaultyConnection();
        }

        return connection;
    }

    @Override
    public IConnection getConnection() {
        return connection;
    }
}
