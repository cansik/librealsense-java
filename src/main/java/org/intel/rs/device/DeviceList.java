package org.intel.rs.device;

import org.bytedeco.librealsense2.rs2_device;
import org.bytedeco.librealsense2.rs2_device_list;
import org.bytedeco.librealsense2.rs2_error;
import org.intel.rs.util.NativeDecorator;
import org.intel.rs.util.NativeList;
import org.intel.rs.util.NativeListIterator;
import org.intel.rs.util.RealSenseError;

import java.util.Iterator;

import static org.bytedeco.librealsense2.global.realsense2.*;
import static org.intel.rs.util.RealSenseUtil.toBoolean;

public class DeviceList implements NativeDecorator<rs2_device_list>, NativeList<Device> {
    protected rs2_device_list instance;

    public DeviceList(rs2_device_list instance) {
        this.instance = instance;
    }

    public boolean contains(Device device) {
        int result = rs2_device_list_contains(instance, device.instance, RealSenseError.getInstance());
        RealSenseError.checkError();
        return toBoolean(result);
    }

    @Override
    public Device get(int index) {
        rs2_device device = rs2_create_device(instance, index, RealSenseError.getInstance());
        RealSenseError.checkError();
        return new Device(device);
    }

    @Override
    public int count() {
        int deviceCount = rs2_get_device_count(instance, RealSenseError.getInstance());
        RealSenseError.checkError();

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
        return new NativeListIterator<>(this);
    }
}
