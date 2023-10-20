package edu.hw2.Task3.ConnectionManagers;

import edu.hw2.Task3.Connections.FaultyConnection;
import edu.hw2.Task3.Connections.IConnection;

public class FaultyConnectionManager implements IConnectionManager {
    private FaultyConnection connection;

    FaultyConnectionManager() {
        connection = new FaultyConnection();
    }

    @Override
    public IConnection getConnection() {
        connection = new FaultyConnection();

        return connection;
    }
}
