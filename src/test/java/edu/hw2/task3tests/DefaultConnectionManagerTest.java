package edu.hw2.task3tests;

import edu.hw2.task3.connectionManagers.DefaultConnectionManager;
import edu.hw2.task3.connections.FaultyConnection;
import edu.hw2.task3.connections.StableConnection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class DefaultConnectionManagerTest {
    @Test
    @DisplayName("Checking that default connection manager returns faulty connection with unlucky random")
    public void faultyConnectionTest() {
        UnluckyRandom unluckyRandom = new UnluckyRandom();

        var defaultConnectionManager = new DefaultConnectionManager(unluckyRandom);
        var receivedConnection = defaultConnectionManager.getConnection();

        assertInstanceOf(FaultyConnection.class, receivedConnection);
    }

    @Test
    @DisplayName("Checking that default connection manager returns stable connection with lucky random")
    public void stableConnectionTest() {
        LuckyRandom luckyRandom = new LuckyRandom();

        var defaultConnectionManager = new DefaultConnectionManager(luckyRandom);
        var receivedConnection = defaultConnectionManager.getConnection();

        assertInstanceOf(StableConnection.class, receivedConnection);
    }
}
