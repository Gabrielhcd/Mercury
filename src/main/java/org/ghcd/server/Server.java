package org.ghcd.server;

import org.ghcd.model.FileModel;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    private ArrayList<FileModel> listOfFiles = new ArrayList<>();
    private ServerConfiguration serverConfiguration = new ServerConfiguration();

    public void openServer() {
        /*try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is Starting in Port 900");
            // Accept the Client request using accept method
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connected");
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            // Here we call receiveFile define new for that
            // file
            /*int fileNameLength = dataInputStream.readInt();
            byte[] fileNameBytes = new byte[fileNameLength];
            dataInputStream.readFully(fileNameBytes, 0, fileNameLength);
            String fileName = new String(fileNameBytes);
            receiveFile(fileName);*/
        try {
            Socket clientSocket = serverConfiguration.createServerConnection();
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            getMultipleFiles();

            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void receiveFile(String fileName) throws Exception {
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        long size = dataInputStream.readLong(); // read file size
        byte[] buffer = new byte[4 * 1024];
        while (size > 0 && (bytes = dataInputStream.read(buffer, 0,
                (int)Math.min(buffer.length, size))) != -1) {
            // Here we write the file using write method
            fileOutputStream.write(buffer, 0, bytes);
            size -= bytes; // read upto file size
        }
        // Here we received file
        System.out.println("File is Received");
        fileOutputStream.close();
    }

    public void getMultipleFiles() {
        try {
            int fileCount = dataInputStream.readInt();
            System.out.println("amountOfFiles: " + fileCount);
            for (int i = 0; i <fileCount; i++ ) {
                int bytes = 0;
                int fileNameLength = dataInputStream.readInt();
                byte[] fileNameBytes = new byte[fileNameLength];
                dataInputStream.readFully(fileNameBytes, 0, fileNameLength);
                String fileName = new String(fileNameBytes);
                FileOutputStream fileOutputStream = new FileOutputStream(fileName);

                long size = dataInputStream.readLong(); // read file size
                byte[] buffer = new byte[4 * 1024];
                long totalBytesRead = 0;
                while (totalBytesRead < size && (bytes = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size - totalBytesRead))) != -1) {
                    fileOutputStream.write(buffer, 0, bytes);
                    totalBytesRead += bytes;
                }
                fileOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345); // Port number
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
                        System.out.println("Received: " + message);
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
