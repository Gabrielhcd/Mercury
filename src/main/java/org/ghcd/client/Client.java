package org.ghcd.client;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private String[] enableProtocols = {"TLSv1.2", "TLSv1.3", "TLSv1.1", "TLSv1", "SLSv3"};
    private DataOutputStream dataOutputStream = null;
    private DataInputStream dataInputStream = null;

    private ClientConfiguration clientConfiguration = new ClientConfiguration();

    public void createClientConnection() {

        try {
            Socket socket = clientConfiguration.createClientConnection();
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("Sending the File to the Server");
            // Call SendFile Method
            sendFile("/home/vanhoemhein/Documents/txts/testFile.txt");
  
              dataInputStream.close();
              dataInputStream.close();
          }
          catch (Exception e) {
            System.out.println("No server found!");
              e.printStackTrace();
          }
    }

    private void sendFile(String path) throws Exception {
        int bytes = 0;
        // Open the File where he located in your pc
        File file = new File(path);
        String fileName = file.getName();
        byte[] fileNameBytes = fileName.getBytes();
        dataOutputStream.writeInt(fileNameBytes.length);
        dataOutputStream.write(fileNameBytes);

        FileInputStream fileInputStream = new FileInputStream(file);

        // Here we send the File to Server
        dataOutputStream.writeLong(file.length());
        // Here we  break file into chunks
        byte[] buffer = new byte[4 * 1024];
        while ((bytes = fileInputStream.read(buffer))!= -1) {
          // Send the file to Server Socket  
            dataOutputStream.write(buffer, 0, bytes);
            dataOutputStream.flush();
        }
        // close the file here
        fileInputStream.close();
    }
    
}
