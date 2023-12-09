package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("MagicNumber")
public class PortScanner {
    private static final Map<Integer, String> KNOWN_PORTS = new HashMap<>();
    private static final String TCP = "TCP";
    private static final String UDP = "UDP";

    static {
        KNOWN_PORTS.put(135, "EPMAP");
        KNOWN_PORTS.put(137, "NetBIOS Name Service");
        KNOWN_PORTS.put(138, "NetBIOS Datagram Service");
        KNOWN_PORTS.put(139, "NetBIOS Session Service");
        KNOWN_PORTS.put(445, "Microsoft-DS (Active Directory)");
        KNOWN_PORTS.put(843, "Adobe Flash");
        KNOWN_PORTS.put(1900, "Simple Service Discovery Protocol (SSDP)");
        KNOWN_PORTS.put(3702, "Dynamic Web Services Discovery");
        KNOWN_PORTS.put(5353, "Multicast DNS");
        KNOWN_PORTS.put(5355, "Link-Local Multicast Name Resolution (LLMNR)");
        KNOWN_PORTS.put(5939, "TeamViewer");
        KNOWN_PORTS.put(6463, "Discord RPC");
        KNOWN_PORTS.put(6942, "Linux-HA High Availability");
        KNOWN_PORTS.put(17500, "Dropbox LAN Sync Protocol");
        KNOWN_PORTS.put(27017, "MongoDB");
        KNOWN_PORTS.put(42420, "ArchiSteamFarm");
    }

    public static List<String> scanPorts(int minPort, int maxPort) {
        List<String> ans = new ArrayList<>();
        for (int port = minPort; port <= maxPort; port++) {
            ans.add("Port " + port + ": " + getPortStatus(port));
        }

        return ans;
    }

    public static String getPortStatus(int port) {
        boolean tcpAvailable = isPortAvailable(port, TCP);
        boolean udpAvailable = isPortAvailable(port, UDP);

        if (tcpAvailable && udpAvailable) {
            return "TCP/UDP - Available";
        } else {
            if (!tcpAvailable) {
                return "TCP - In use by " + getKnownService(port);
            }
            return "UDP - In use by " + getKnownService(port);
        }
    }

    private static boolean isPortAvailable(int port, String protocol) {
        try {
            if (protocol.equals(TCP)) {
                try (ServerSocket ignored = new ServerSocket(port)) {
                    return true;
                }
            } else if (protocol.equals(UDP)) {
                try (DatagramSocket ignored = new DatagramSocket(port)) {
                    return true;
                } catch (SocketException e) {
                    // Порт занят
                }
            }
        } catch (IOException e) {
            //Порт занят
        }
        return false;
    }

    private static String getKnownService(int port) {
        return KNOWN_PORTS.getOrDefault(port, "unknown service");
    }

}
