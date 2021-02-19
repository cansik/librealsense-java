package org.intel.rs.device;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.CharPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.librealsense2.rs2_device;
import org.bytedeco.librealsense2.rs2_error;
import org.bytedeco.librealsense2.rs2_raw_data_buffer;
import org.intel.rs.types.Extension;

import java.nio.charset.StandardCharsets;

import static org.bytedeco.librealsense2.global.realsense2.*;
import org.intel.rs.util.RealSenseError;
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
        IntPointer result = new IntPointer(1);
        rs2_is_enabled(instance, result, RealSenseError.getInstance());
        RealSenseError.checkError();
        return toBoolean(result.get());
    }

    public void setAdvancedModeEnabled(boolean value) {
        rs2_toggle_advanced_mode(instance, value ? 1 : 0, RealSenseError.getInstance());
        RealSenseError.checkError();
    }

    public String getJsonConfiguration() {
        rs2_raw_data_buffer buffer = rs2_serialize_json(instance, RealSenseError.getInstance());
        int size = rs2_get_raw_data_size(buffer, RealSenseError.getInstance());
        BytePointer data = rs2_get_raw_data(buffer, RealSenseError.getInstance());
        RealSenseError.checkError();

        return data.getString();
    }

    public void setJsonConfiguration(String value) {

        byte[] data = value.getBytes(StandardCharsets.UTF_8);
        BytePointer dataPointer = new BytePointer(data);
        rs2_load_json(instance, dataPointer, data.length, RealSenseError.getInstance());
        RealSenseError.checkError();
    }
}
