package edu.hw2.task3.connectionManagers;

import edu.hw2.task3.connections.FaultyConnection;
import edu.hw2.task3.connections.IConnection;

public class FaultyConnectionManager implements IConnectionManager {
    @Override
    public IConnection getConnection() {
        return new FaultyConnection();
    }
}
