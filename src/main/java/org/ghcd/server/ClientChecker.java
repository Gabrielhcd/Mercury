package org.ghcd.server;

import org.ghcd.data.Devices;

public class ClientChecker {

    private static Devices devices = new Devices();

    public static Boolean isClientKnown(String clientIp) {
        if (!devices.getDevice(clientIp, null).getDeviceName().isEmpty()) {
            return true;
        }
        return false;
    }
}
