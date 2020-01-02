package org.intel.rs.processing;

import org.bytedeco.librealsense2.global.realsense2;

public class HoleFillingFilter extends FilterProcessingBlock {
    public HoleFillingFilter() {
        super(realsense2::rs2_create_hole_filling_filter_block);
    }
}
