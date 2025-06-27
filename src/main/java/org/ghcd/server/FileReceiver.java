package org.ghcd.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ghcd.data.FileSearch;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileReceiver {

    public static Logger logger = LogManager.getLogger(FileReceiver.class);

    public void getMultipleFiles(DataInputStream dataInputStream) {
        logger.info("Entered getMultipleFiles...");
        try {
            int fileCount = dataInputStream.readInt();
            logger.info("amountOfFiles: {}", fileCount);
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
                    FileSearch.fileAlreadyExists("", fileName);
                    fileOutputStream.write(buffer, 0, bytes);
                    totalBytesRead += bytes;
                }
                fileOutputStream.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
