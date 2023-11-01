package edu.hw2.task3tests;

import edu.hw2.task3.PopularCommandExecutor;
import edu.hw2.task3.connectionManagers.DefaultConnectionManager;
import edu.hw2.task3.connections.IConnection;
import edu.hw2.task3.exceptions.ExceedingConnectionAttemptsException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class PopularCommandExecutorTest {

    @Test
    @DisplayName("Maximum attempts limit less than 1, exception expected")
    public void incorrectMaximumAttemptsLimit() {
        var manager = new DefaultConnectionManager();

        assertThrows(IllegalArgumentException.class, () -> new PopularCommandExecutor(manager, -1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 15, 50})
    @DisplayName("Checking that an exception is thrown after the maximum number of attempts")
    public void exceedingMaximumAttemptsLimit(int maxAttempts) {
        var unluckyRandom = new UnluckyRandom();
        var manager = new DefaultConnectionManager(unluckyRandom);
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);

        assertThrows(ExceedingConnectionAttemptsException.class, executor::updatePackages);
    }

    @Test
    @DisplayName("Checking the correct execution of the command and closing the connection")
    void successfulConnection() {
        var luckyRandom = new LuckyRandom();

        var connectionManager = new DefaultConnectionManager(luckyRandom);

        DefaultConnectionManager mockConnectionManager = Mockito.spy(connectionManager);

        IConnection mockConnection = Mockito.mock(IConnection.class);

        Mockito.when(mockConnectionManager.getConnection()).thenReturn(mockConnection);

        PopularCommandExecutor commandExecutor = new PopularCommandExecutor(mockConnectionManager, 5);

        commandExecutor.updatePackages();

        Mockito.verify(mockConnectionManager, Mockito.times(1)).getConnection();

        Mockito.verify(mockConnection, Mockito.times(1)).
            execute("apt update && apt upgrade -y");

        Mockito.verify(mockConnection, Mockito.times(1)).close();
    }
}
