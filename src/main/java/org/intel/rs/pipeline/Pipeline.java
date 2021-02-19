package org.intel.rs.pipeline;

import org.intel.rs.util.RealSenseError;
import static org.bytedeco.librealsense2.global.realsense2.*;
import static org.intel.rs.util.RealSenseUtil.toBoolean;

import org.bytedeco.librealsense2.*;
import org.intel.rs.Context;
import org.intel.rs.frame.FrameList;
import org.intel.rs.util.NativeDecorator;

public class Pipeline implements NativeDecorator<rs2_pipeline> {
    rs2_pipeline instance;
    private Context context;

    public Pipeline(rs2_pipeline instance) {
        this.instance = instance;
    }

    public Pipeline(Context context) {
        this.context = context;
        instance = rs2_create_pipeline(context.getInstance(), RealSenseError.getInstance());
        RealSenseError.checkError();
    }

    public Pipeline() {
        context = new Context();
        instance = rs2_create_pipeline(context.getInstance(), RealSenseError.getInstance());
        RealSenseError.checkError();
    }

    public PipelineProfile start() {
        rs2_pipeline_profile ptr = rs2_pipeline_start(instance, RealSenseError.getInstance());
        RealSenseError.checkError();
        return new PipelineProfile(ptr);
    }

    public PipelineProfile start(Config config) {
        rs2_pipeline_profile ptr = rs2_pipeline_start_with_config(instance, config.getInstance(), RealSenseError.getInstance());
        RealSenseError.checkError();
        return new PipelineProfile(ptr);
    }

    public void stop() {
        rs2_pipeline_stop(instance, RealSenseError.getInstance());
        RealSenseError.checkError();
    }

    public FrameList waitForFrames() {
        return waitForFrames(5000);
    }

    public FrameList waitForFrames(int timeout_ms) {
        rs2_frame ptr = rs2_pipeline_wait_for_frames(instance, timeout_ms, RealSenseError.getInstance());
        RealSenseError.checkError();
        return new FrameList(ptr);
    }

    public boolean pollForFrames(FrameList result) {
        boolean valid = toBoolean(rs2_pipeline_poll_for_frames(instance, result.getInstance(), RealSenseError.getInstance()));
        RealSenseError.checkError();
        return valid;
    }

    @Override
    public rs2_pipeline getInstance() {
        return instance;
    }

    @Override
    public void release() {
        rs2_delete_pipeline(instance);
    }
}
