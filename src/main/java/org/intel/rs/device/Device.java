package org.intel.rs.device;

import org.intel.rs.types.CameraInfo;
import org.intel.rs.types.Extension;
import org.intel.rs.util.NativeDecorator;
import org.intel.rs.sensor.SensorList;
import static org.intel.rs.util.RealSenseUtil.*;
import static org.bytedeco.librealsense2.global.realsense2.*;
import org.bytedeco.librealsense2.*;

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
        return getInfo(RS2_CAMERA_INFO_NAME);
    }

    public String getSerialNumber() {
        return getInfo(RS2_CAMERA_INFO_SERIAL_NUMBER);
    }

    public String getFirmwareVersion() {
        return getInfo(RS2_CAMERA_INFO_FIRMWARE_VERSION);
    }

    public String getRecommendedFirmwareVersion() {
        return getInfo(RS2_CAMERA_INFO_RECOMMENDED_FIRMWARE_VERSION);
    }

    public String getPhysicalPort() {
        return getInfo(RS2_CAMERA_INFO_PHYSICAL_PORT);
    }

    public String getDebugOpCode() {
        return getInfo(RS2_CAMERA_INFO_DEBUG_OP_CODE);
    }

    public boolean isInAdvancedMode() {
        return toBoolean(getInfo(RS2_CAMERA_INFO_ADVANCED_MODE));
    }

    public String getProductId() {
        return getInfo(RS2_CAMERA_INFO_PRODUCT_ID);
    }

    public boolean isLocked() {
        return toBoolean(getInfo(RS2_CAMERA_INFO_CAMERA_LOCKED));
    }

    public String getUSBTypeDescriptor() {
        return getInfo(RS2_CAMERA_INFO_USB_TYPE_DESCRIPTOR);
    }

    public String getInfo(CameraInfo info) {
        return getInfo(info.getIndex());
    }

    public String getInfo(int info) {
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

    public boolean isExtendableTo(Extension extension) {
        rs2_error error = new rs2_error();
        int result = rs2_is_device_extendable_to(instance, extension.getIndex(), error);
        checkError(error);
        return toBoolean(result);
    }

    // todo: implement advanced device
    // todo: implement playback device
    // todo: implement record device
}
