package org.ghcd;

import org.ghcd.server.Server;

public class Main {

    private static Server server = new Server();

    public static void main(String[] args) {
        try {
            server.openServer();
        } catch (Exception e) {
            e.getStackTrace();
        }

    }
}