package org.ghcd.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerConfiguration {

    private Integer socketPort = 12345;
    Socket clientSocket;

    public static Logger logger = LogManager.getLogger(ServerConfiguration.class);

    public Socket createServerConnection() {

        logger.info("Entered createServerConnection...");
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.setReuseAddress(true);
            serverSocket.bind(new InetSocketAddress(socketPort));
            logger.info("Server starting in port: {}", serverSocket);
            clientSocket = serverSocket.accept();
            logger.info("Connected to client: {}", clientSocket.getInetAddress());
            logger.info("Ref: {}", System.identityHashCode(clientSocket));

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clientSocket;


    }
}
