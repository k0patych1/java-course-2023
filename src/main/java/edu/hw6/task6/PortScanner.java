package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PortScanner {
    private final static Logger LOGGER = LogManager.getLogger();

    private final static int MAX_PORT = 49151;

    private final static String UDP = "UDP";

    private final static String TCP = "TCP";

    private final static Map<Integer, String> PORTS_AND_APPS = Map.of(
        80, "HTTP",
        21, "FTP",
        25, "SMTP",
        22, "SSH",
        3306, "MySQL Database",
        843, "Adobe Flash",
        548, "Apple Filing Protocol",
        3702, "Dynamic Web Services Discovery"
    );

    private PortScanner() {}

    public static List<PortInfo> checkAllPorts() {
        List<PortInfo> ports = new ArrayList<>();
        for (int port = 0; port <= MAX_PORT; ++port) {
            boolean isTcpOpen = isTcpOpen(port);
            boolean isUdpOpen = isUdpOpen(port);

            if (isTcpOpen && isUdpOpen) {
                ports.add(new PortInfo(port, UDP + '|' + TCP, false));
            } else if (isUdpOpen) {
                ports.add(new PortInfo(port, UDP, false));
            } else if (isTcpOpen) {
                ports.add(new PortInfo(port, TCP, false));
            } else {
                ports.add(new PortInfo(port, UDP + '|' + TCP, true));
            }
        }
        return ports;
    }

    public static boolean isTcpOpen(int port) {
        try (ServerSocket tcpSocket = new ServerSocket(port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean isUdpOpen(int port) {
        try (DatagramSocket udpSocket = new DatagramSocket(port)) {
            return true;
        } catch (SocketException e) {
            return false;
        }
    }

    public static void scan() {
        List<PortInfo> ports = checkAllPorts();

        for (PortInfo portInfo : ports) {
            LOGGER.info(
                portInfo.protocol() + "  " + portInfo.port() + "  "
                + (portInfo.isBusy() ? "Closed" : "Open") + "  "
                + PORTS_AND_APPS.getOrDefault(portInfo.port(), ""));
        }
    }
}
