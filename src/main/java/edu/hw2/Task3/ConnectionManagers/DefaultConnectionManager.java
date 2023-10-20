package edu.hw2.Task3.ConnectionManagers;

import edu.hw2.Task3.Connections.FaultyConnection;
import edu.hw2.Task3.Connections.IConnection;
import edu.hw2.Task3.Connections.StableConnection;
import java.util.Random;

public class DefaultConnectionManager implements IConnectionManager {
    private IConnection connection;

    private void createConnection() {
        Random random = new Random();
        boolean isStableIConnection = random.nextBoolean();

        if (isStableIConnection) {
            connection =  new StableConnection();
        } else {
            connection =  new FaultyConnection();
        }
    }

    public DefaultConnectionManager() {
        createConnection();
    }

    @Override
    public IConnection getConnection() {
        createConnection();

        return connection;
    }
}
