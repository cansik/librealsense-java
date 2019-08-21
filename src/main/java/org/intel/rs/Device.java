package org.intel.rs;

import static org.intel.rs.api.RealSense.*;
import static org.intel.rs.api.RealSenseUtil.*;

public class Device implements AutoCloseable {
    protected rs2_device instance;

    public Device(rs2_device instance) {
        this.instance = instance;
    }

    public String getDeviceInfo(rs2_camera_info info) {
        // check if info is supported
        rs2_error error = new rs2_error();
        boolean isSupported = toBoolean(rs2_supports_device_info(instance, info, error));
        checkError(error);

        if(!isSupported)
            return null;

        // read device info
        String infoText = rs2_get_device_info(instance, info, error).getString();
        checkError(error);

        return infoText;
    }

    @Override
    public void close() {
        rs2_delete_device(instance);
    }
}
