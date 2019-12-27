package org.intel.rs.processing;

import static org.intel.rs.util.RealSenseUtil.checkError;
import static org.bytedeco.librealsense2.global.realsense2.*;

import org.bytedeco.librealsense2.rs2_error;
import org.intel.rs.frame.FrameQueue;
import org.intel.rs.frame.VideoFrame;

public class Colorizer extends ProcessingBlock {

    private final FrameQueue queue = new FrameQueue(1);

    public Colorizer() {
        rs2_error error = new rs2_error();
        instance = rs2_create_colorizer(error);
        checkError(error);

        rs2_start_processing_queue(instance, queue.getInstance(), error);
        checkError(error);
    }

    public VideoFrame colorize(VideoFrame original)
    {
        rs2_error error = new rs2_error();
        rs2_frame_add_ref(original.getInstance(), error);
        checkError(error);
        rs2_process_frame(instance, original.getInstance(), error);
        checkError(error);
        return (VideoFrame)queue.waitForFrame();
    }
}
