package org.intel.rs;

import static org.intel.rs.api.RealSense.*;
import static org.intel.rs.api.RealSenseUtil.*;

public class Context implements AutoCloseable {
    protected rs2_context instance;

    public Context() {
        rs2_error error = new rs2_error();
        instance = rs2_create_context(RS2_API_VERSION, error);
        checkError(error);

        // setup device changed callback
        // todo: fix callback => add callbacks to generation
        rs2_devices_changed_callback changedCallback = new rs2_devices_changed_callback();
        //rs2_set_devices_changed_callback_cpp(instance, changedCallback, error);
        //checkError(error);
    }

    public DeviceList queryDevices() {
        rs2_error error = new rs2_error();
        rs2_device_list devices = rs2_query_devices(instance, error);
        checkError(error);

        return new DeviceList(devices);
    }

    @Override
    public void close() {
        rs2_delete_context(instance);
    }
}
