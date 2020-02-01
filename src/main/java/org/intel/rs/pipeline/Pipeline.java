package org.intel.rs.pipeline;

import static org.intel.rs.util.RealSenseUtil.checkError;
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
        rs2_error error = new rs2_error();
        instance = rs2_create_pipeline(context.getInstance(), error);
        checkError(error);
    }

    public Pipeline() {
        context = new Context();
        rs2_error error = new rs2_error();
        instance = rs2_create_pipeline(context.getInstance(), error);
        checkError(error);
    }

    public PipelineProfile start() {
        rs2_error error = new rs2_error();
        rs2_pipeline_profile ptr = rs2_pipeline_start(instance, error);
        checkError(error);
        return new PipelineProfile(ptr);
    }

    public PipelineProfile start(Config config) {
        rs2_error error = new rs2_error();
        rs2_pipeline_profile ptr = rs2_pipeline_start_with_config(instance, config.getInstance(), error);
        checkError(error);
        return new PipelineProfile(ptr);
    }

    public void stop() {
        rs2_error error = new rs2_error();
        rs2_pipeline_stop(instance, error);
        checkError(error);
    }

    public FrameList waitForFrames() {
        return waitForFrames(5000);
    }

    public FrameList waitForFrames(int timeout_ms) {
        rs2_error error = new rs2_error();
        rs2_frame ptr = rs2_pipeline_wait_for_frames(instance, timeout_ms, error);
        checkError(error);
        return new FrameList(ptr);
    }

    public boolean pollForFrames(FrameList result) {
        rs2_error error = new rs2_error();
        boolean valid = toBoolean(rs2_pipeline_poll_for_frames(instance, result.getInstance(), error));
        checkError(error);
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
