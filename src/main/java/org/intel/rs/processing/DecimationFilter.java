package org.intel.rs.processing;

import org.bytedeco.librealsense2.global.realsense2;

public class DecimationFilter extends FilterProcessingBlock {
    public DecimationFilter() {
        super(realsense2::rs2_create_decimation_filter_block);
    }
}
