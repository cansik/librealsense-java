package org.inte.rs.test;

import org.intel.rs.Context;
import org.intel.rs.Device;
import org.intel.rs.DeviceList;
import org.intel.rs.api.RealSense;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DeviceTest {

    private Context context;

    @Before
    public void createContext() {
        context = new Context();
    }

    @After
    public void closeContext() {
        context.close();
    }

    @Test
    public void deviceCountTest() {
        DeviceList list = context.queryDevices();
        int count = list.count();
        System.out.println("Device Count: " + count);
        list.close();
    }

    @Test
    public void getNativeDeviceInfo() {
        DeviceList list = context.queryDevices();
        int count = list.count();
        if(count < 1) return;

        Device device = list.get(0);
        String info = device.getInfo(RealSense.rs2_camera_info.RS2_CAMERA_INFO_NAME);
        System.out.println("Name: " + info);
        device.close();
    }

    @Test
    public void getSimpleDeviceInfo() {
        DeviceList list = context.queryDevices();
        int count = list.count();
        if(count < 1) return;

        Device device = list.get(0);
        System.out.println("Name: " + device.getName());
        System.out.println("In Advanced Mode: " + device.isInAdvancedMode());
        device.close();
    }

}
