package edu.hw6;

import edu.hw6.task6.PortInfo;
import edu.hw6.task6.PortScanner;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {
    @Test
    public void portStatusTest() {
        try (ServerSocket serverSocket = new ServerSocket(4000);) {
            assertFalse(PortScanner.isTcpOpen(4000));
            assertTrue(PortScanner.isUdpOpen(4000));
        } catch (IOException ignored) {

        }

        try(DatagramSocket datagramSocket = new DatagramSocket(4000);) {
            assertTrue(PortScanner.isTcpOpen(4000));
            assertFalse(PortScanner.isUdpOpen(4000));
        } catch (SocketException ignored) {
        }

        assertTrue(PortScanner.isTcpOpen(4000));
        assertTrue(PortScanner.isUdpOpen(4000));
    }

    @Test
    public void portScannerTest() {
        List<PortInfo> ports = PortScanner.checkAllPorts();
        int maxPort = 49151;

        assertThat(ports.size()).isEqualTo(maxPort + 1);
        assertThat(ports.get(81)).isEqualTo(new PortInfo(4000, "UDP|TCP", false));
    }
}
