package org.ghcd.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ghcd.model.FileModel;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private ArrayList<FileModel> listOfFiles = new ArrayList<>();
    private ServerConfiguration serverConfiguration = new ServerConfiguration();
    private FileReceiver fileReceiver = new FileReceiver();
    private Integer socketPort = 12345;

    public static Logger logger = LogManager.getLogger(Server.class);

    public void openServer() throws IOException {
        logger.info("Entered openServer...");
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
        ServerSocket serverSocket = new ServerSocket(socketPort);
        while (true) {
            logger.info("Server starting in port: {}", serverSocket);

            //clientSocket = serverConfiguration.createServerConnection();
            try {
                Socket clientSocket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                fileReceiver.getMultipleFiles(dataInputStream);

                try {
                    if (dataInputStream != null) {
                        dataInputStream.close();
                        logger.info("DataInputStream closed.");
                    }
                    if (dataOutputStream != null) {
                        dataOutputStream.close();
                        logger.info("DataOutputStream closed.");
                    }
                    logger.info("Ref: {}", System.identityHashCode(clientSocket));
                    clientSocket.close();
                    logger.info("clientSocket closed.");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    /*private static void receiveFile(String fileName) throws Exception {
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
    }*/

    /*public void getMultipleFiles() {
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

    }*/


}
