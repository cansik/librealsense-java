package org.intel.rs.option;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.librealsense2.rs2_error;
import org.bytedeco.librealsense2.rs2_options;
import org.bytedeco.librealsense2.rs2_sensor;
import org.intel.rs.types.Option;

import static org.bytedeco.librealsense2.global.realsense2.*;
import static org.intel.rs.util.RealSenseUtil.checkError;
import static org.intel.rs.util.RealSenseUtil.toBoolean;

public class CameraOption {
    private rs2_options options;
    private Option option;

    private float min;
    private float max;
    private float step;
    private float defaultValue;
    private String description = "";

    public CameraOption(rs2_sensor sensor, Option option) {
        this.options = new rs2_options(sensor);
        this.option = option;

        if (isSupported()) {
            FloatPointer min = new FloatPointer(1);
            FloatPointer max = new FloatPointer(1);
            FloatPointer step = new FloatPointer(1);
            FloatPointer defaultValue = new FloatPointer(1);

            rs2_error error = new rs2_error();
            rs2_get_option_range(options, option.getIndex(), min, max, step, defaultValue, error);
            checkError(error);

            this.min = min.get();
            this.max = max.get();
            this.step = step.get();
            this.defaultValue = defaultValue.get();

            BytePointer strPtr = rs2_get_option_description(options, option.getIndex(), error);
            checkError(error);

            description = strPtr.getString();
        }
    }

    public boolean isSupported() {
        try {
            rs2_error error = new rs2_error();
            boolean result = toBoolean(rs2_supports_option(options, option.getIndex(), error));
            checkError(error);
            return result;
        } catch (Exception ex) {
            return false;
        }
    }

    public Option getKey() {
        return option;
    }

    public String getDescription() {
        return description;
    }

    public float getValue() {
        rs2_error error = new rs2_error();
        float value = rs2_get_option(options, option.getIndex(), error);
        checkError(error);
        return value;
    }

    public void setValue(float value) {
        rs2_error error = new rs2_error();
        rs2_set_option(options, option.getIndex(), value, error);
        checkError(error);
    }

    public String getValueDescription(float value) {
        rs2_error error = new rs2_error();
        BytePointer strPtr = rs2_get_option_value_description(options, option.getIndex(), value, error);
        checkError(error);
        return strPtr.toString();
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    public float getStep() {
        return step;
    }

    public float getDefault() {
        return defaultValue;
    }

    public boolean isReadOnly() {
        rs2_error error = new rs2_error();
        boolean result = toBoolean(rs2_is_option_read_only(options, option.getIndex(), error));
        checkError(error);
        return result;
    }
}