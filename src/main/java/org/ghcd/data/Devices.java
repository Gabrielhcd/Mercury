package org.ghcd.data;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

import org.ghcd.model.DeviceModel;

import java.io.File;
import java.io.FileReader;

public class Devices {

    File file;
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    BufferedReader bufferedReader;

    public void saveNewDevice(String filePath, String deviceName, String deviceIp) {
        try {
            file = new File(filePath);
            fileWriter = new FileWriter(filePath, true);
            bufferedWriter = new BufferedWriter(fileWriter);

            if (!deviceAlreadyExists(filePath, deviceIp, deviceName)) {
                return;
            }
            
            bufferedWriter.write(deviceName);
            bufferedWriter.write('-');
            bufferedWriter.write(deviceIp);
            bufferedWriter.newLine();
            bufferedWriter.close();
            System.out.println(deviceName + " saved!");

        } catch (IOException e) {
            System.out.println("Error saving new device!");
            e.printStackTrace();
        }

    }

    public Boolean deviceAlreadyExists(String filePath, String deviceIp, String deviceName) {
        if (deviceIp == null && deviceName == null) {
            System.out.println("Enter either device Ip or Name");
            return false;
        }

        if (deviceName == null) {
            if (getDevice(filePath, deviceIp, deviceName).getDeviceIp().equals(deviceIp)) {
                System.out.println("This Ip already exists");
                return false;
            }
        }
        
        if(deviceIp == null) {
            if (getDevice(filePath, deviceIp, deviceName).getDeviceName().equals(deviceName)) {
                System.out.println("This deviceName already exists");
                return false;
            }
        }
        
        return true;
    }

    public DeviceModel getDevice(String filePath, String deviceIp, String deviceName) {
        DeviceModel deviceModel = new DeviceModel();
        String[] deviceParams = new String[1];
        String fileLine;
        String searchParam = "";

        if (deviceIp == null) {
            searchParam = deviceName;
        }
        if (deviceName == null) {
            searchParam = deviceIp;
        }
        if (deviceIp == null && deviceName == null) {
            System.out.println("Enter device Ip or Name for search!");
        }

        try{
            bufferedReader = new BufferedReader(new FileReader(filePath));
            while ((fileLine = bufferedReader.readLine()) != null) {
                if (fileLine.contains(searchParam)) {
                    deviceParams = fileLine.split("-");
                }
            }
            deviceModel.setDeviceName(deviceParams[0]);
            deviceModel.setDeviceIp(deviceParams[1]);
        } catch (Exception e) {
            System.out.println("Error reading IPs file");
        }
        return deviceModel;
    }
    
}
