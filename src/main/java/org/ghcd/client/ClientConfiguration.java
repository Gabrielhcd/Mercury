package org.ghcd.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class ClientConfiguration {

    private Socket socket;
    private String mainPCIp = "192.168.0.26";
    private Integer socketPort = 12345;
    private Integer socketTimeOutInMilis = 5000;
    
    public Socket createClientConnection() {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(mainPCIp, socketPort), socketTimeOutInMilis);
        } catch (SocketException e) {
            System.out.println("No server was found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error creating connetion to server: " + mainPCIp);
        }
        return socket;
    }
}
