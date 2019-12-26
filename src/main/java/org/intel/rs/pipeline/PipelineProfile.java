package org.intel.rs.pipeline;

import static org.intel.rs.util.RealSenseUtil.checkError;
import static org.bytedeco.librealsense2.global.realsense2.*;

import org.bytedeco.librealsense2.*;
import org.intel.rs.Context;
import org.intel.rs.util.NativeDecorator;

public class PipelineProfile implements NativeDecorator<rs2_pipeline_profile> {
    rs2_pipeline_profile instance;

    public PipelineProfile(rs2_pipeline_profile instance) {
        this.instance = instance;
    }

    @Override
    public rs2_pipeline_profile getInstance() {
        return instance;
    }

    @Override
    public void release() {
        rs2_delete_pipeline_profile(instance);
    }
}
