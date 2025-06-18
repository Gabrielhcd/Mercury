package org.ghcd;

<<<<<<< Updated upstream
import org.ghcd.server.Server;
=======
import org.ghcd.Server.Server;
import org.ghcd.client.Client;
>>>>>>> Stashed changes

public class Main {

    private static Server server = new Server();
    private static Client client = new Client();

    public static void main(String[] args) {
        try {
<<<<<<< Updated upstream
            server.openServer();
=======
            client.createClientConnection();
>>>>>>> Stashed changes
        } catch (Exception e) {
            e.getStackTrace();
        }

    }
}