package org.ghcd.model;

public class DeviceModel {
    private String deviceName;
    private String deviceIp;
    private Integer pingPort;
    private Integer fileTransferPort;

    public DeviceModel() {}

    public String getDeviceName() {
        return deviceName;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public Integer getPingPort() {
        return pingPort;
    }

    public Integer getFileTransferPort() {
        return fileTransferPort;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    public void setPingPort(Integer pingPort) {
        this.pingPort = pingPort;
    }

    public void setFileTransferPort(Integer fileTransferPort) {
        this.fileTransferPort = fileTransferPort;
    }
}
