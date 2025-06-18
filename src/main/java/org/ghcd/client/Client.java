package org.ghcd.client;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.net.Socket;

public class Client {
    
    private DataOutputStream dataOutputStream = null;
    private DataInputStream dataInputStream = null;
    private Socket socket;
    private String mainPCIp = "192.168.0.26";
    private Integer socketPort = 12345;

    public void createClientConnection() {

        try (Socket socket = new Socket(mainPCIp, socketPort)) {
           
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("Sending the File to the Server");
            // Call SendFile Method
            sendFile("/home/vanhoemhein/Documents/txts/testFile.txt");
  
              dataInputStream.close();
              dataInputStream.close();
          }
          catch (Exception e) {
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
