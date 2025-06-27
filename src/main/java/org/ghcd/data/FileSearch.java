package org.ghcd.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ghcd.server.ServerConfiguration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSearch {

    public static Logger logger = LogManager.getLogger(FileSearch.class);

    public static void fileAlreadyExists(String dirPath, String fileName) {
        logger.info("Entered fileAlreadyExists...");
        Path filePath = Paths.get(dirPath, fileName);
        if (Files.exists(filePath)) {
            logger.info("Overriding existing files");
        }
    }
}
