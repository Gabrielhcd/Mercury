package org.ghcd;

import org.ghcd.Server.Server;

import java.io.IOException;

public class Main {

    private static Server server = new Server();

    public static void main(String[] args) {
        try {
            server.startServer();
        } catch (IOException e) {
            e.getStackTrace();
        }

    }
}