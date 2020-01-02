package org.intel.rs.processing;

import org.bytedeco.librealsense2.global.realsense2;

public class ZeroOrderInvalidationFilter extends FilterProcessingBlock {
    public ZeroOrderInvalidationFilter() {
        super(realsense2::rs2_create_zero_order_invalidation_block);
    }
}
