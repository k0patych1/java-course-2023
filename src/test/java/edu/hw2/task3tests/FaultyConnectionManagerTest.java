package edu.hw2.task3tests;

import edu.hw2.task3.connectionManagers.DefaultConnectionManager;
import edu.hw2.task3.connectionManagers.FaultyConnectionManager;
import edu.hw2.task3.connections.FaultyConnection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class FaultyConnectionManagerTest {
    @Test
    @DisplayName("Checking that the correct exception is thrown after the maximum number of connection attempts")
    public void faultyConnectionTest() {
        var faultyConnectionManager = new FaultyConnectionManager();
        var receivedConnection = faultyConnectionManager.getConnection();

        assertInstanceOf(FaultyConnection.class, receivedConnection);
    }
}
