package org.ghcd.model;
import java.io.File;
import java.util.UUID;

public class FileModel {

    private UUID Id;
    private File file;
    private String fileName;
    private byte[] fileData;
    private Long fileSize;
    private String fileExtension;

    public FileModel(int id, String fileName, byte[] fileData, String fileExtension) {
        Id = UUID.randomUUID();
        this.fileName = fileName;
        this.fileData = fileData;
        this.fileExtension = fileExtension;
    }

    public FileModel() {
        this.Id = UUID.randomUUID();
    }

    public UUID getId() {
        return Id;
    }

    public File getFile() {
        return file;
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

    public void setFile(File file) {
        this.file = file;
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

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
