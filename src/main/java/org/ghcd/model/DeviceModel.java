package org.ghcd.model;

public class DeviceModel {
    private String deviceName;
    private String deviceIp;

    public DeviceModel() {}

    public String getDeviceName() {
        return deviceName;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }
}
