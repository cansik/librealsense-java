package org.intel.rs.pipeline;

import org.intel.rs.util.RealSenseError;
import static org.bytedeco.librealsense2.global.realsense2.*;

import org.bytedeco.librealsense2.*;
import org.intel.rs.Context;
import org.intel.rs.device.Device;
import org.intel.rs.stream.StreamProfileList;
import org.intel.rs.types.Stream;
import org.intel.rs.util.NativeDecorator;

public class PipelineProfile implements NativeDecorator<rs2_pipeline_profile> {
    rs2_pipeline_profile instance;

    public PipelineProfile(rs2_pipeline_profile instance) {
        this.instance = instance;
    }

    public Device getDevice() {
        rs2_device ptr = rs2_pipeline_profile_get_device(instance, RealSenseError.getInstance());
        RealSenseError.checkError();
        return new Device(ptr);
    }

    public StreamProfileList getStreams() {
        rs2_stream_profile_list ptr = rs2_pipeline_profile_get_streams(instance, RealSenseError.getInstance());
        RealSenseError.checkError();
        return new StreamProfileList(ptr);
    }

    // todo: implement get stream

    @Override
    public rs2_pipeline_profile getInstance() {
        return instance;
    }

    @Override
    public void release() {
        rs2_delete_pipeline_profile(instance);
    }
}
