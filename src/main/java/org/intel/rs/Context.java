package org.intel.rs;

import org.bytedeco.javacpp.Pointer;
import org.intel.rs.device.DeviceList;
import org.intel.rs.device.OnDevicesChangedCallback;
import org.intel.rs.util.NativeDecorator;

import static org.bytedeco.librealsense2.global.realsense2.*;

import org.bytedeco.librealsense2.*;

import static org.intel.rs.util.RealSenseUtil.*;

public class Context implements NativeDecorator<rs2_context> {
    protected rs2_context instance;
    private OnDevicesChangedCallback onDevicesChangedCallback;
    private rs2_devices_changed_callback_ptr devicesChangedCallbackPointer;

    public Context() {
        rs2_error error = new rs2_error();
        instance = rs2_create_context(RS2_API_VERSION, error);
        checkError(error);

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

        rs2_set_devices_changed_callback(instance, devicesChangedCallbackPointer, null, error);
        checkError(error);
    }

    public DeviceList queryDevices() {
        rs2_error error = new rs2_error();
        rs2_device_list devices = rs2_query_devices(instance, error);
        checkError(error);

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
