package edu.hw2.task3tests;

import edu.hw2.task3.connections.FaultyConnection;
import edu.hw2.task3.exceptions.ConnectionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FaultyConnectionTest {
    @Test
    @DisplayName("Checking that connection can work with lucky random")
    public void successfulExecuteTest() {
        LuckyRandom luckyRandom = new LuckyRandom();
        FaultyConnection faultyConnection = new FaultyConnection(luckyRandom);
        assertDoesNotThrow(() -> faultyConnection.execute("Some command"));
        faultyConnection.close();
    }

    @Test
    @DisplayName("Checking that connection throws connection exception")
    public void unsuccessfulExecuteTest() {
        UnluckyRandom unluckyRandom = new UnluckyRandom();
        FaultyConnection faultyConnection = new FaultyConnection(unluckyRandom);
        assertThrows(ConnectionException.class, () -> faultyConnection.execute("Some command"));
        faultyConnection.close();
    }
}
