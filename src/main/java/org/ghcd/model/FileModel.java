package org.ghcd.model;

public class FileModel {

    private int Id;
    private String fileName;
    private byte[] fileData;
    private String fileExtension;

    public FileModel(int id, String fileName, byte[] fileData, String fileExtension) {
        Id = id;
        this.fileName = fileName;
        this.fileData = fileData;
        this.fileExtension = fileExtension;
    }

    public int getId() {
        return Id;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
}
