package org.ghcd.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerConfiguration {

    private Integer socketPort = 12345;
    private Socket clientSocket;

    public Socket createServerConnection() {
        try {
            ServerSocket serverSocket = new ServerSocket(socketPort);
            System.out.println("Server starting in port: " + serverSocket);
            clientSocket = serverSocket.accept();
            System.out.println("Connected to client: " + clientSocket.getInetAddress());
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return clientSocket;
    }
}
