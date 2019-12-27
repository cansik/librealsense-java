package org.intel.rs.processing;

import static org.intel.rs.util.RealSenseUtil.checkError;
import static org.bytedeco.librealsense2.global.realsense2.*;

import org.bytedeco.librealsense2.*;
import org.intel.rs.Context;
import org.intel.rs.device.Device;
import org.intel.rs.stream.StreamProfileList;
import org.intel.rs.types.Stream;
import org.intel.rs.util.NativeDecorator;

public class ProcessingBlock implements NativeDecorator<rs2_processing_block> {
    rs2_processing_block instance;

    public ProcessingBlock() {

    }

    public ProcessingBlock(rs2_processing_block instance) {
        this.instance = instance;
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
