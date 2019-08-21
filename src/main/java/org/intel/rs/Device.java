package org.intel.rs;

import static org.intel.rs.api.RealSense.*;
import static org.intel.rs.api.RealSenseUtil.*;

public class Device implements AutoCloseable {
    protected rs2_device instance;

    public Device(rs2_device instance) {
        this.instance = instance;
    }

    //region Device Info
    public String getName() {
        return getDeviceInfo(rs2_camera_info.RS2_CAMERA_INFO_NAME);
    }

    public String getSerialNumber() {
        return getDeviceInfo(rs2_camera_info.RS2_CAMERA_INFO_SERIAL_NUMBER);
    }

    public String getFirmwareVersion() {
        return getDeviceInfo(rs2_camera_info.RS2_CAMERA_INFO_FIRMWARE_VERSION);
    }

    public String getRecommendedFirmwareVersion() {
        return getDeviceInfo(rs2_camera_info.RS2_CAMERA_INFO_RECOMMENDED_FIRMWARE_VERSION);
    }

    public String getPhysicalPort() {
        return getDeviceInfo(rs2_camera_info.RS2_CAMERA_INFO_PHYSICAL_PORT);
    }

    public String getDebugOpCode() {
        return getDeviceInfo(rs2_camera_info.RS2_CAMERA_INFO_DEBUG_OP_CODE);
    }

    public boolean isInAdvancedMode() {
        return toBoolean(getDeviceInfo(rs2_camera_info.RS2_CAMERA_INFO_ADVANCED_MODE));
    }

    public String getProductId() {
        return getDeviceInfo(rs2_camera_info.RS2_CAMERA_INFO_PRODUCT_ID);
    }

    public boolean isLocked() {
        return toBoolean(getDeviceInfo(rs2_camera_info.RS2_CAMERA_INFO_CAMERA_LOCKED));
    }

    public String getUSBTypeDescriptor() {
        return getDeviceInfo(rs2_camera_info.RS2_CAMERA_INFO_USB_TYPE_DESCRIPTOR);
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
    //endregion

    @Override
    public void close() {
        rs2_delete_device(instance);
    }
}
