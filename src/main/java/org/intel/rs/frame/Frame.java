package org.intel.rs.frame;

import org.bytedeco.javacpp.Pointer;
import org.intel.rs.util.NativeDecorator;
import org.intel.rs.sensor.Sensor;
import org.intel.rs.stream.StreamProfile;
import org.intel.rs.util.TimeStampDomain;

import java.nio.ByteBuffer;

import static org.bytedeco.librealsense2.global.realsense2.*;
import org.bytedeco.librealsense2.*;
import static org.intel.rs.util.RealSenseUtil.checkError;
import static org.intel.rs.util.RealSenseUtil.toBoolean;

public class Frame implements NativeDecorator<rs2_frame> {
    rs2_frame instance;

    public Frame(rs2_frame instance) {
        this.instance = instance;
    }

    public boolean isExtendableTo(int extension) {
        rs2_error error = new rs2_error();
        int result = rs2_is_frame_extendable_to(instance, extension, error);
        checkError(error);

        return toBoolean(result);
    }

    public Frame clone() {
        rs2_error error = new rs2_error();
        rs2_frame_add_ref(instance, error);
        checkError(error);

        return new Frame(instance);
    }

    public void keep() {
        rs2_keep_frame(instance);
    }

    public boolean isComposite() {
        return isExtendableTo(RS2_EXTENSION_COMPOSITE_FRAME);
    }

    public ByteBuffer getData() {
        rs2_error error = new rs2_error();
        Pointer dataPtr = rs2_get_frame_data(instance, error);
        checkError(error);

        return dataPtr.asByteBuffer();
    }

    public StreamProfile getProfile()
    {
        rs2_error error = new rs2_error();
        rs2_stream_profile streamProfile = rs2_get_frame_stream_profile(instance, error);
        checkError(error);

        return new StreamProfile(streamProfile);
    }

    public <T extends StreamProfile> T getProfileEx() {
        return (T)getProfile();
    }

    public long getNumber() {
        rs2_error error = new rs2_error();
        long result = rs2_get_frame_number(instance, error);
        checkError(error);
        return result;
    }

    public double getTimestamp() {
        rs2_error error = new rs2_error();
        double result = rs2_get_frame_timestamp(instance, error);
        checkError(error);
        return result;
    }

    public TimeStampDomain getTimeStampDomain() {
        rs2_error error = new rs2_error();
        int result = rs2_get_frame_timestamp_domain(instance, error);
        checkError(error);
        return new TimeStampDomain(result);
    }

    public Sensor getSensor() {
        rs2_error error = new rs2_error();
        rs2_sensor result = rs2_get_frame_sensor(instance, error);
        checkError(error);
        return new Sensor(result);
    }

    // region Meta Data Information
    public double getMetaData(int value) {
        rs2_error error = new rs2_error();
        double result = rs2_get_frame_metadata(instance, value, error);
        checkError(error);
        return result;
    }

    public boolean isMetaDataSupported(int value) {
        rs2_error error = new rs2_error();
        int result = rs2_supports_frame_metadata(instance, value, error);
        checkError(error);
        return toBoolean(result);
    }

    public int getFrameCounter() {
        return (int)getMetaData(RS2_FRAME_METADATA_FRAME_COUNTER);
    }

    public double getFrameTimeStamp() {
        return getMetaData(RS2_FRAME_METADATA_FRAME_TIMESTAMP);
    }

    public double getSensorTimeStamp() {
        return getMetaData(RS2_FRAME_METADATA_SENSOR_TIMESTAMP);
    }

    public double getActualExposure() {
        return getMetaData(RS2_FRAME_METADATA_ACTUAL_EXPOSURE);
    }

    public int getGainLevel() {
        return (int)getMetaData(RS2_FRAME_METADATA_GAIN_LEVEL);
    }

    public double getAutoExposure() {
        return getMetaData(RS2_FRAME_METADATA_AUTO_EXPOSURE);
    }

    public double getWhiteBalance() {
        return getMetaData(RS2_FRAME_METADATA_WHITE_BALANCE);
    }

    public double getTimeOfArrival() {
        return getMetaData(RS2_FRAME_METADATA_TIME_OF_ARRIVAL);
    }

    public double getTemperature() {
        return getMetaData(RS2_FRAME_METADATA_TEMPERATURE);
    }

    public double getBackendTimeStamp() {
        return getMetaData(RS2_FRAME_METADATA_BACKEND_TIMESTAMP);
    }

    public double getActualFPS() {
        return getMetaData(RS2_FRAME_METADATA_ACTUAL_FPS);
    }

    public double getFrameLaserPower() {
        return getMetaData(RS2_FRAME_METADATA_FRAME_LASER_POWER);
    }

    public boolean isLaserPowerModeOn() {
        return toBoolean((int)getMetaData(RS2_FRAME_METADATA_FRAME_LASER_POWER_MODE));
    }

    public double getExposurePriority() {
        return getMetaData(RS2_FRAME_METADATA_EXPOSURE_PRIORITY);
    }

    public double getExposureROILeft() {
        return getMetaData(RS2_FRAME_METADATA_EXPOSURE_ROI_LEFT);
    }

    public double getExposureROIRight() {
        return getMetaData(RS2_FRAME_METADATA_EXPOSURE_ROI_RIGHT);
    }

    public double getExposureROITop() {
        return getMetaData(RS2_FRAME_METADATA_EXPOSURE_ROI_TOP);
    }

    public double getExposureROIBottom() {
        return getMetaData(RS2_FRAME_METADATA_EXPOSURE_ROI_BOTTOM);
    }

    public double getBrightness() {
        return getMetaData(RS2_FRAME_METADATA_BRIGHTNESS);
    }

    public double getContrast() {
        return getMetaData(RS2_FRAME_METADATA_CONTRAST);
    }

    public double getSaturation() {
        return getMetaData(RS2_FRAME_METADATA_SATURATION);
    }

    public double getSharpness() {
        return getMetaData(RS2_FRAME_METADATA_SHARPNESS);
    }

    public double getAutoWhiteBalanceTemperature() {
        return getMetaData(RS2_FRAME_METADATA_AUTO_WHITE_BALANCE_TEMPERATURE);
    }

    public double getBacklightCompensation() {
        return getMetaData(RS2_FRAME_METADATA_BACKLIGHT_COMPENSATION);
    }

    public double getHue() {
        return getMetaData(RS2_FRAME_METADATA_HUE);
    }

    public double getGamma() {
        return getMetaData(RS2_FRAME_METADATA_GAMMA);
    }

    public double getManualWhiteBalance() {
        return getMetaData(RS2_FRAME_METADATA_MANUAL_WHITE_BALANCE);
    }


    public double getPowerLineFrequency() {
        return getMetaData(RS2_FRAME_METADATA_POWER_LINE_FREQUENCY);
    }


    public double getLowLightCompensation() {
        return getMetaData(RS2_FRAME_METADATA_LOW_LIGHT_COMPENSATION);
    }
    // endregion

    @Override
    public rs2_frame getInstance() {
        return instance;
    }

    @Override
    public void release() {
        rs2_release_frame(instance);
    }
}
