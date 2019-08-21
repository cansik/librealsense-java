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
        int count = list.getCount();
        System.out.println("Device Count: " + count);
    }

    @Test
    public void getDeviceInfo() {
        DeviceList list = context.queryDevices();
        int count = list.getCount();
        if(count < 0) return;

        Device device = list.get(0);
        String info = device.getDeviceInfo(RealSense.rs2_camera_info.RS2_CAMERA_INFO_NAME);
        System.out.println("Name: " + info);
    }

}
