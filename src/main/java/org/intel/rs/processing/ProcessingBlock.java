package org.intel.rs.processing;

import org.bytedeco.librealsense2.rs2_processing_block;
import org.bytedeco.librealsense2.rs2_sensor;
import org.intel.rs.option.SensorOptions;
import org.intel.rs.util.NativeDecorator;

import static org.bytedeco.librealsense2.global.realsense2.rs2_delete_processing_block;

public class ProcessingBlock implements NativeDecorator<rs2_processing_block> {
    rs2_processing_block instance;
    SensorOptions options;

    public ProcessingBlock() {
    }

    public ProcessingBlock(rs2_processing_block instance) {
        this.instance = instance;
    }

    public SensorOptions getOptions() {
        // todo: make thread safe
        if (options == null) {
            rs2_sensor sensor = new rs2_sensor(instance);
            options = new SensorOptions(sensor);
        }

        return options;
    }

    @Override
    public rs2_processing_block getInstance() {
        return instance;
    }

    @Override
    public void release() {
        rs2_delete_processing_block(instance);
    }
}
