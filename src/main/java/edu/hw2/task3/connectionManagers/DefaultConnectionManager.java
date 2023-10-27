package edu.hw2.task3.connectionManagers;

import edu.hw2.task3.connections.FaultyConnection;
import edu.hw2.task3.connections.IConnection;
import edu.hw2.task3.connections.StableConnection;
import java.util.Random;

public class DefaultConnectionManager implements IConnectionManager {
    public Random random;

    public DefaultConnectionManager() {
        random = new Random();
    }

    public DefaultConnectionManager(Random random) {
        this.random = random;
    }

    @Override
    public IConnection getConnection() {
        boolean isStableIConnection = random.nextBoolean();

        if (isStableIConnection) {
            return new StableConnection();
        }

        return new FaultyConnection(random);
    }
}
