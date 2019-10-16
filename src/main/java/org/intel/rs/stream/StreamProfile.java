package org.intel.rs.stream;

import org.intel.rs.util.NativeDecorator;

import static org.bytedeco.librealsense2.global.realsense2.*;
import org.bytedeco.librealsense2.*;

public class StreamProfile implements NativeDecorator<rs2_stream_profile> {
    rs2_stream_profile instance;

    public StreamProfile(rs2_stream_profile instance)
    {
        this.instance = instance;
    }

    @Override
    public rs2_stream_profile getInstance() {
        return instance;
    }

    @Override
    public void release() {
        rs2_delete_stream_profile(instance);
    }
}
