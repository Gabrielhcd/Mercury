package org.ghcd.Listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ghcd.server.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener {

    private Integer listenerPort = 8888;
    private Server server = new Server();
    public static Logger logger = LogManager.getLogger(Listener.class);


    public void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(listenerPort); // Port number
        System.out.println("Server started. Waiting for clients...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected from " + clientSocket.getInetAddress());

            // Handle communication with the client (using threads)
            new Thread(() -> {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                    String message;
                    while ((message = reader.readLine()) != null) {

                        writer.println("Server received: " + message);
                    }
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
