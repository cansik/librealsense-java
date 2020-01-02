package org.intel.rs.processing;

import org.bytedeco.librealsense2.global.realsense2;

public class TemporalFilter extends FilterProcessingBlock {
    public TemporalFilter() {
        super(realsense2::rs2_create_temporal_filter_block);
    }
}