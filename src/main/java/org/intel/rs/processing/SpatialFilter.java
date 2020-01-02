package org.intel.rs.processing;

import org.bytedeco.librealsense2.global.realsense2;

public class SpatialFilter extends FilterProcessingBlock {
    public SpatialFilter() {
        super(realsense2::rs2_create_spatial_filter_block);
    }
}