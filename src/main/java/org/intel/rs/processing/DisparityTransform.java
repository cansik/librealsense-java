package org.intel.rs.processing;

import org.intel.rs.util.RealSenseError;

import static org.bytedeco.librealsense2.global.realsense2.rs2_create_disparity_transform_block;

public class DisparityTransform extends FilterProcessingBlock {

    public DisparityTransform() {
        this(true);
    }

    public DisparityTransform(boolean transformToDisparity) {
        super(error -> rs2_create_disparity_transform_block(transformToDisparity ? (byte) 1 : (byte) 0, RealSenseError.getInstance()));
    }
}
