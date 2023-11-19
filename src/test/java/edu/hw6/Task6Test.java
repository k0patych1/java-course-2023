package edu.hw6;

import edu.hw6.task6.PortInfo;
import edu.hw6.task6.PortScanner;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {
    @Test
    public void portStatusTest() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(80);) {
            assertFalse(PortScanner.isTcpOpen(80));
            assertTrue(PortScanner.isUdpOpen(80));
        }

        try(DatagramSocket datagramSocket = new DatagramSocket(80);) {
            assertTrue(PortScanner.isTcpOpen(80));
            assertFalse(PortScanner.isUdpOpen(80));
        }

        assertTrue(PortScanner.isTcpOpen(80));
        assertTrue(PortScanner.isUdpOpen(80));
    }

    @Test
    public void portScannerTest() {
        List<PortInfo> ports = PortScanner.checkAllPorts();
        int maxPort = 49151;

        assertThat(ports.size()).isEqualTo(maxPort + 1);
        assertThat(ports.get(80)).isEqualTo(new PortInfo(80, "UDP|TCP", false));
    }
}
