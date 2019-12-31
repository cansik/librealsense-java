package org.intel.rs.option;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.librealsense2.rs2_error;
import org.bytedeco.librealsense2.rs2_options;
import org.bytedeco.librealsense2.rs2_sensor;
import org.intel.rs.types.Option;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.bytedeco.librealsense2.global.realsense2.rs2_get_option_value_description;
import static org.intel.rs.util.RealSenseUtil.checkError;

public class SensorOptions implements Iterable<CameraOption> {
    private static List<Option> optionValues = Arrays.asList(Option.class.getEnumConstants());

    private rs2_sensor sensor;
    private List<CameraOption> supportedOptions;

    public SensorOptions(rs2_sensor sensor) {
        this.sensor = sensor;
    }

    public CameraOption get(Option option) {
        return new CameraOption(sensor, option);
    }

    public String getOptionValueDescription(Option option, float value) {
        rs2_options options = new rs2_options(sensor);

        rs2_error error = new rs2_error();
        BytePointer desc = rs2_get_option_value_description(options, option.getIndex(), value, error);
        checkError(error);

        if (!desc.isNull()) {
            return desc.getString();
        }
        return null;
    }

    @Override
    public Iterator<CameraOption> iterator() {
        // todo: make thread safe
        if(supportedOptions == null) {
            supportedOptions = new ArrayList<>();

            for(Option option : optionValues) {
                CameraOption cameraOption = get(option);

                if(cameraOption.isSupported()) {
                    supportedOptions.add(cameraOption);
                }
            }
        }

        return supportedOptions.iterator();
    }
}