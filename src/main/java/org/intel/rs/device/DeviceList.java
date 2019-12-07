package org.intel.rs.device;

import org.intel.rs.sensor.Sensor;
import org.intel.rs.util.NativeDecorator;

import static org.bytedeco.librealsense2.global.realsense2.*;

import org.bytedeco.librealsense2.*;
import org.intel.rs.util.NativeList;
import org.intel.rs.util.NativeListIterator;

import java.util.Iterator;

import static org.intel.rs.util.RealSenseUtil.checkError;
import static org.intel.rs.util.RealSenseUtil.toBoolean;

public class DeviceList implements NativeDecorator<rs2_device_list>, NativeList<Device> {
    protected rs2_device_list instance;

    public DeviceList(rs2_device_list instance) {
        this.instance = instance;
    }

    public boolean contains(Device device) {
        rs2_error error = new rs2_error();
        int result = rs2_device_list_contains(instance, device.instance, error);
        checkError(error);
        return toBoolean(result);
    }

    @Override
    public Device get(int index) {
        rs2_error error = new rs2_error();
        rs2_device device = rs2_create_device(instance, index, error);
        checkError(error);
        return new Device(device);
    }

    @Override
    public int count() {
        rs2_error error = new rs2_error();
        int deviceCount = rs2_get_device_count(instance, error);
        checkError(error);

        return deviceCount;
    }

    @Override
    public rs2_device_list getInstance() {
        return instance;
    }

    @Override
    public void release() {
        rs2_delete_device_list(instance);
    }

    @Override
    public Iterator<Device> iterator() {
        return new NativeListIterator(this);
    }
}
