package org.intel.rs.processing;

import org.bytedeco.librealsense2.rs2_error;
import org.bytedeco.librealsense2.rs2_frame;
import org.intel.rs.frame.Frame;
import org.intel.rs.frame.FrameList;
import org.intel.rs.frame.FrameQueue;

import static org.bytedeco.librealsense2.global.realsense2.*;
import static org.intel.rs.util.RealSenseUtil.checkError;
import static org.intel.rs.util.RealSenseUtil.toBoolean;

public class Syncer extends ProcessingBlock {
    private final FrameQueue queue = new FrameQueue(1);

    public Syncer()
    {
        rs2_error error = new rs2_error();
        instance = rs2_create_sync_processing_block(error);
        checkError(error);

        rs2_start_processing_queue(instance, queue.getInstance(), error);
        checkError(error);
    }

    public void submitFrame(Frame frame)
    {
        rs2_error error = new rs2_error();
        rs2_frame_add_ref(frame.getInstance(), error);
        checkError(error);

        rs2_process_frame(instance, frame.getInstance(), error);
        checkError(error);
    }

    public FrameList waitForFrames() {
        return waitForFrames(5000);
    }

    public FrameList waitForFrames(int timeout)
    {
        rs2_error error = new rs2_error();
        rs2_frame ptr = rs2_wait_for_frame(queue.getInstance(), timeout, error);
        checkError(error);
        return new FrameList(ptr);
    }

    public boolean pollForFrames(FrameList result)
    {
        rs2_error error = new rs2_error();
        int res = rs2_poll_for_frame(queue.getInstance(), result.getInstance(), error);
        checkError(error);

        return toBoolean(res);
    }
}
