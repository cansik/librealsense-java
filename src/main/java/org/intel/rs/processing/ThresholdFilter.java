package org.intel.rs.processing;

import org.bytedeco.librealsense2.global.realsense2;

public class ThresholdFilter extends FilterProcessingBlock {
    public ThresholdFilter() {
        super(realsense2::rs2_create_threshold);
    }
}
