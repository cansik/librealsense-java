package org.intel.rs.processing;

import org.bytedeco.librealsense2.rs2_error;
import org.bytedeco.librealsense2.rs2_frame;
import org.intel.rs.frame.Frame;
import org.intel.rs.frame.FrameList;
import org.intel.rs.frame.FrameQueue;

import static org.bytedeco.librealsense2.global.realsense2.*;
import org.intel.rs.util.RealSenseError;
import static org.intel.rs.util.RealSenseUtil.toBoolean;

public class Syncer extends ProcessingBlock {
    private final FrameQueue queue = new FrameQueue(1);

    public Syncer()
    {
        instance = rs2_create_sync_processing_block(RealSenseError.getInstance());
        RealSenseError.checkError();

        rs2_start_processing_queue(instance, queue.getInstance(), RealSenseError.getInstance());
        RealSenseError.checkError();
    }

    public void submitFrame(Frame frame)
    {
        rs2_frame_add_ref(frame.getInstance(), RealSenseError.getInstance());
        RealSenseError.checkError();

        rs2_process_frame(instance, frame.getInstance(), RealSenseError.getInstance());
        RealSenseError.checkError();
    }

    public FrameList waitForFrames() {
        return waitForFrames(5000);
    }

    public FrameList waitForFrames(int timeout)
    {
        rs2_frame ptr = rs2_wait_for_frame(queue.getInstance(), timeout, RealSenseError.getInstance());
        RealSenseError.checkError();
        return new FrameList(ptr);
    }

    public boolean pollForFrames(FrameList result)
    {
        int res = rs2_poll_for_frame(queue.getInstance(), result.getInstance(), RealSenseError.getInstance());
        RealSenseError.checkError();

        return toBoolean(res);
    }
}
