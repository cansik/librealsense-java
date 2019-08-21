package org.intel.rs;

import static org.intel.rs.api.RealSense.*;
import static org.intel.rs.api.RealSenseUtil.*;

public class Sensor implements AutoCloseable  {
    rs2_sensor instance;

    public Sensor(rs2_sensor instance) {
        this.instance = instance;
    }

    //region Sensor Info
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
        boolean isSupported = toBoolean(rs2_supports_sensor_info(instance, info, error));
        checkError(error);

        if(!isSupported)
            return null;

        // read device info
        String infoText = rs2_get_sensor_info(instance, info, error).getString();
        checkError(error);

        return infoText;
    }
    //endregion

    @Override
    public void close() {
        rs2_delete_sensor(instance);
    }

    // todo: implement sensor roi settings (AutoExposureSettings)

    // todo: implement sensor options

    // todo: implement open, start, stop, close

    public float getDepthScale() {
        rs2_error error = new rs2_error();
        float depthScale = rs2_get_depth_scale(instance, error);
        checkError(error);

        return depthScale;
    }

    // todo: implement getStreamProfiles
}