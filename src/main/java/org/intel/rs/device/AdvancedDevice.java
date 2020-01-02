package org.intel.rs.device;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.CharPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.librealsense2.rs2_device;
import org.bytedeco.librealsense2.rs2_error;
import org.bytedeco.librealsense2.rs2_raw_data_buffer;
import org.intel.rs.types.Extension;

import static org.bytedeco.librealsense2.global.realsense2.*;
import static org.intel.rs.util.RealSenseUtil.checkError;
import static org.intel.rs.util.RealSenseUtil.toBoolean;

public class AdvancedDevice extends Device {

    public AdvancedDevice(rs2_device instance) {
        super(instance);
    }

    public static AdvancedDevice fromDevice(Device device) {
        if (!device.isExtendableTo(Extension.AdvancedMode))
            throw new RuntimeException("Device does not support AdvancedMode");

        return new AdvancedDevice(device.instance);
    }

    public boolean isAdvancedModeEnabled() {
        rs2_error error = new rs2_error();
        IntPointer result = new IntPointer(1);
        rs2_is_enabled(instance, result, error);
        checkError(error);
        return toBoolean(result.get());
    }

    public void setAdvancedModeEnabled(boolean value) {
        rs2_error error = new rs2_error();
        rs2_toggle_advanced_mode(instance, value ? 1 : 0, error);
        checkError(error);
    }

    public String getJsonConfiguration() {
        rs2_error error = new rs2_error();
        rs2_raw_data_buffer buffer = rs2_serialize_json(instance, error);
        int size = rs2_get_raw_data_size(buffer, error);
        BytePointer data = rs2_get_raw_data(buffer, error);
        checkError(error);

        CharPointer charPointer = new CharPointer(data.address());
        charPointer.capacity(size);

        return charPointer.getString();
    }

    public void setJsonConfiguration(String value) {
        rs2_error error = new rs2_error();

        CharPointer charPointer = new CharPointer(value);
        rs2_load_json(instance, charPointer, value.length(), error);
        checkError(error);
    }
}
