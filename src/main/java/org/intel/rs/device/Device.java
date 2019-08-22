package org.intel.rs.device;

import org.intel.rs.util.NativeDecorator;
import org.intel.rs.sensor.SensorList;

import static org.intel.rs.api.RealSense.*;
import static org.intel.rs.util.RealSenseUtil.*;

public class Device implements NativeDecorator<rs2_device> {
    protected rs2_device instance;

    public Device(rs2_device instance) {
        this.instance = instance;
    }

    public SensorList querySensors() {
        rs2_error error = new rs2_error();
        rs2_sensor_list sensors = rs2_query_sensors(instance, error);
        checkError(error);

        return new SensorList(sensors);
    }

    //region Device Info
    public String getName() {
        return getInfo(rs2_camera_info.RS2_CAMERA_INFO_NAME);
    }

    public String getSerialNumber() {
        return getInfo(rs2_camera_info.RS2_CAMERA_INFO_SERIAL_NUMBER);
    }

    public String getFirmwareVersion() {
        return getInfo(rs2_camera_info.RS2_CAMERA_INFO_FIRMWARE_VERSION);
    }

    public String getRecommendedFirmwareVersion() {
        return getInfo(rs2_camera_info.RS2_CAMERA_INFO_RECOMMENDED_FIRMWARE_VERSION);
    }

    public String getPhysicalPort() {
        return getInfo(rs2_camera_info.RS2_CAMERA_INFO_PHYSICAL_PORT);
    }

    public String getDebugOpCode() {
        return getInfo(rs2_camera_info.RS2_CAMERA_INFO_DEBUG_OP_CODE);
    }

    public boolean isInAdvancedMode() {
        return toBoolean(getInfo(rs2_camera_info.RS2_CAMERA_INFO_ADVANCED_MODE));
    }

    public String getProductId() {
        return getInfo(rs2_camera_info.RS2_CAMERA_INFO_PRODUCT_ID);
    }

    public boolean isLocked() {
        return toBoolean(getInfo(rs2_camera_info.RS2_CAMERA_INFO_CAMERA_LOCKED));
    }

    public String getUSBTypeDescriptor() {
        return getInfo(rs2_camera_info.RS2_CAMERA_INFO_USB_TYPE_DESCRIPTOR);
    }

    public String getInfo(rs2_camera_info info) {
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
    //endregion

    @Override
    public rs2_device getInstance() {
        return instance;
    }

    @Override
    public void release() {
        rs2_delete_device(instance);
    }

    // todo: implement advanced device
    // todo: implement playback device
    // todo: implement record device
}
