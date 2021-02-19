package org.intel.rs.test;

import org.intel.rs.util.RealSenseError;
import org.junit.Test;

import static org.bytedeco.librealsense2.global.realsense2.*;
import org.bytedeco.librealsense2.*;

public class NativeAPITest {

    @Test
    public void createContext() {
        rs2_context context = rs2_create_context(RS2_API_VERSION, RealSenseError.getInstance());
        assert RealSenseError.getInstance().isNull();
    }

    @Test
    public void countDevices() {

        // create context
        rs2_context context = rs2_create_context(RS2_API_VERSION, RealSenseError.getInstance());
        assert RealSenseError.getInstance().isNull();

        // list devices
        rs2_device_list devices = rs2_query_devices(context, RealSenseError.getInstance());
        assert RealSenseError.getInstance().isNull();

        // count devices
        int deviceCount = rs2_get_device_count(devices, RealSenseError.getInstance());
        assert RealSenseError.getInstance().isNull();

        System.out.println("Device Count: " + deviceCount);
    }
}
