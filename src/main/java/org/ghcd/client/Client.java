package org.ghcd.client;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.ArrayList;

import org.ghcd.model.FileModel;

public class Client {
    private String[] enableProtocols = {"TLSv1.2", "TLSv1.3", "TLSv1.1", "TLSv1", "SLSv3"};
    private DataOutputStream dataOutputStream = null;
    private DataInputStream dataInputStream = null;
    private ClientConfiguration clientConfiguration = new ClientConfiguration();
    private FileSender fileSender = new FileSender();
    private String[] listOfFilesToSend = {"/home/vanhoemhein/Documents/txts/testFile.txt",
                                    "/home/vanhoemhein/Documents/txts/secondFile.txt"};
    private ArrayList<FileModel> files = new ArrayList<>();
    public void dataIOConfiguration() {

        try {
            Socket socket = clientConfiguration.createClientConnection();
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("Sending the File to the Server");
            // Call SendFile Method
            //sendFile("/home/vanhoemhein/Documents/txts/testFile.txt");
            files = fileSender.addFilesToList(listOfFilesToSend);
            sendMultipleFiles();
  
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
          }
          catch (IOException e) {
            System.out.println("Data IO error!");
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

    private void sendMultipleFiles() throws IOException {
        int bytes = 0;
        byte[] buffer = new byte[4 * 1024];
        try {
            dataOutputStream.writeInt(files.size());
            for (FileModel file: files) {
                dataOutputStream.writeInt(file.getFileName().length());
                dataOutputStream.write(file.getFileName().getBytes());
                FileInputStream fileInputStream = new FileInputStream(file.getFile());
                dataOutputStream.writeLong(file.getFile().length());

                while ((bytes = fileInputStream.read(buffer))!= -1) {
                    dataOutputStream.write(buffer, 0, bytes);
                    dataOutputStream.flush();
                }
                fileInputStream.close();
            }
        } catch (IOException e) {
            System.out.println("Error sending multiple files!");
            e.printStackTrace();
        }
    }
    
}
