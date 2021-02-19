package org.intel.rs;

import org.bytedeco.javacpp.Pointer;
import org.intel.rs.device.DeviceList;
import org.intel.rs.device.OnDevicesChangedCallback;
import org.intel.rs.util.NativeDecorator;

import static org.bytedeco.librealsense2.global.realsense2.*;

import org.bytedeco.librealsense2.*;
import org.intel.rs.util.RealSenseError;

import static org.intel.rs.util.RealSenseUtil.*;

public class Context implements NativeDecorator<rs2_context> {
    protected rs2_context instance;
    private OnDevicesChangedCallback onDevicesChangedCallback;
    private rs2_devices_changed_callback_ptr devicesChangedCallbackPointer;

    public Context() {
        instance = rs2_create_context(RS2_API_VERSION, RealSenseError.getInstance());
        RealSenseError.checkError();

        // setup device changed callback
        devicesChangedCallbackPointer = new rs2_devices_changed_callback_ptr() {
            @Override
            public void call(rs2_device_list removedListPtr, rs2_device_list addedListPtr, Pointer userDataPtr) {
                OnDevicesChangedCallback callback = onDevicesChangedCallback;
                if (callback != null) {
                    // todo: check for memory leak
                    DeviceList removedList = new DeviceList(removedListPtr);
                    DeviceList addedList = new DeviceList(addedListPtr);

                    callback.onChanged(removedList, addedList);
                }
            }
        };

        rs2_set_devices_changed_callback(instance, devicesChangedCallbackPointer, null, RealSenseError.getInstance());
        RealSenseError.checkError();
    }

    public DeviceList queryDevices() {
        rs2_device_list devices = rs2_query_devices(instance, RealSenseError.getInstance());
        RealSenseError.checkError();

        return new DeviceList(devices);
    }

    @Override
    public rs2_context getInstance() {
        return instance;
    }

    @Override
    public void release() {
        rs2_delete_context(instance);
    }

    public OnDevicesChangedCallback getOnDevicesChangedCallback() {
        return onDevicesChangedCallback;
    }

    public void setOnDevicesChangedCallback(OnDevicesChangedCallback onDevicesChangedCallback) {
        this.onDevicesChangedCallback = onDevicesChangedCallback;
    }
}
