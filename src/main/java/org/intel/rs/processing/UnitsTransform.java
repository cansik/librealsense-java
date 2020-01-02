package org.intel.rs.processing;

import org.bytedeco.librealsense2.global.realsense2;

public class UnitsTransform extends FilterProcessingBlock {
    public UnitsTransform() {
        super(realsense2::rs2_create_units_transform);
    }
}
