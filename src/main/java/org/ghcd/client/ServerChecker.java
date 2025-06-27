package org.ghcd.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.ghcd.data.Devices;
import org.ghcd.model.DeviceModel;

public class ServerChecker {
    
    private Integer timeOutInMilis = 5000;


    public Boolean isDeviceAvailable(String deviceIp, int port) {
        
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(deviceIp, port), timeOutInMilis);
            socket.close();
            return true;
            
        } catch (IOException e) {
            System.out.println(deviceIp + "not available!");
            e.printStackTrace();
            return false;
        }
    }
}
