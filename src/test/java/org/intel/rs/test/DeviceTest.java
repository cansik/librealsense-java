package org.intel.rs.test;

import org.intel.rs.Context;
import org.intel.rs.device.AdvancedDevice;
import org.intel.rs.device.Device;
import org.intel.rs.device.DeviceList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.bytedeco.librealsense2.global.realsense2.RS2_CAMERA_INFO_NAME;

public class DeviceTest {

    private Context context;

    @Before
    public void createContext() {
        context = new Context();
    }

    @After
    public void closeContext() {
        context.release();
    }

    @Test
    public void deviceCountTest() {
        DeviceList list = context.queryDevices();
        int count = list.count();
        System.out.println("Device Count: " + count);
        list.release();
    }

    @Test
    public void getNativeDeviceInfo() {
        DeviceList list = context.queryDevices();
        int count = list.count();
        if(count < 1) return;

        Device device = list.get(0);
        String info = device.getInfo(RS2_CAMERA_INFO_NAME);
        System.out.println("Name: " + info);
        device.release();
        list.release();
    }

    @Test
    public void getSimpleDeviceInfo() {
        DeviceList list = context.queryDevices();
        int count = list.count();
        if(count < 1) return;

        Device device = list.get(0);
        System.out.println("Name: " + device.getName());
        System.out.println("In Advanced Mode: " + device.isInAdvancedMode());
        device.release();
        list.release();
    }

    @Test
    public void getJsonConfigTest() {
        DeviceList list = context.queryDevices();
        int count = list.count();
        if(count < 1) return;

        Device device = list.get(0);
        AdvancedDevice advancedDevice = AdvancedDevice.fromDevice(device);
        advancedDevice.setAdvancedModeEnabled(true);

        String config = advancedDevice.getJsonConfiguration();
        assert(!config.equals(""));

        device.release();
        list.release();
    }

    @Test
    public void setJsonConfigTest() {
        String param = "\"controls-laserstate\": \"off\"";
        String jsonConfig = "{" + param + "}";

        DeviceList list = context.queryDevices();
        int count = list.count();
        if(count < 1) return;

        Device device = list.get(0);
        AdvancedDevice advancedDevice = AdvancedDevice.fromDevice(device);
        advancedDevice.setAdvancedModeEnabled(true);

        advancedDevice.setJsonConfiguration(jsonConfig);

        // check if config has been set
        String config = advancedDevice.getJsonConfiguration();
        assert(config.contains(param));

        device.release();
        list.release();
    }
}
