package org.ghcd.client;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.ghcd.model.FileModel;

public class FileSender {

    private ArrayList<FileModel> fileList = new ArrayList<>();
    FileInputStream fileInputStream;

    public ArrayList<FileModel> addFilesToList(String[] listOfFiles) {
        for (String filePath: listOfFiles) {
            File file = new File(filePath);
            FileModel fileModel = new FileModel();
            fileModel.setFileName(file.getName());
            fileModel.setFile(file);
            fileList.add(fileModel);
        }
        return fileList;
    }

}
