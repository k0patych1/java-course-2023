package edu.hw2;

import edu.hw2.Task3.ConnectionManagers.DefaultConnectionManager;
import edu.hw2.Task3.PopularCommandExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class Task3Test {
    @Test
    @DisplayName("Logger and exceptions test")
    public void loggerExceptionTest() {
        var connectionManager = new DefaultConnectionManager();

        var executor = new PopularCommandExecutor(connectionManager, 2);

        try {
            executor.updatePackages();
        }  catch (Exception e) {
            String out = executor.getLoggerInformation();

            assertThat(out).isEqualTo("Connection was closed\n");
            assertThat(e.getMessage()).isEqualTo("The number of attempts to execute a command has been exceeded\n");
            assertTrue(e.getCause().toString().contains("Failed to execute command on server\n"));

            return;
        }

        String out = executor.getLoggerInformation();

        assertThat(out).isEqualTo("""
                Command "apt update && apt upgrade -y" was successfully executed
                Connection was closed
                """);
    }
}
