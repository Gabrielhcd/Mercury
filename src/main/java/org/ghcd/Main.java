package org.ghcd;

import org.ghcd.server.Server;
import org.ghcd.client.Client;

public class Main {

    private static Server server = new Server();
    private static Client client = new Client();

    public static void main(String[] args) {
        try {
            client.createClientConnection();
        } catch (Exception e) {
            e.getStackTrace();
        }

    }
}