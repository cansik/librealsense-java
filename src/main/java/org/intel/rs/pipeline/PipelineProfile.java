package org.intel.rs.pipeline;

import static org.intel.rs.util.RealSenseUtil.checkError;
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
        rs2_error error = new rs2_error();
        rs2_device ptr = rs2_pipeline_profile_get_device(instance, error);
        checkError(error);
        return new Device(ptr);
    }

    public StreamProfileList getStreams() {
        rs2_error error = new rs2_error();
        rs2_stream_profile_list ptr = rs2_pipeline_profile_get_streams(instance, error);
        checkError(error);
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
