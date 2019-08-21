package org.intel.rs;

import static org.intel.rs.api.RealSense.*;
import static org.intel.rs.api.RealSenseUtil.checkError;
import static org.intel.rs.api.RealSenseUtil.toBoolean;

public class FrameQueue implements Releasable  {
    rs2_frame_queue instance;

    public FrameQueue() {
        this(1);
    }

    public FrameQueue(int capacity) {
        rs2_error error = new rs2_error();
        instance = rs2_create_frame_queue(capacity, error);
        checkError(error);
    }

    public boolean pollForFrame(Frame frame)
    {
        rs2_error error = new rs2_error();
        int res = rs2_poll_for_frame(instance, frame.instance, error);
        checkError(error);

        return toBoolean(res);
    }

    public Frame waitForFrame() {
        return waitForFrame(5000);
    }

    public Frame waitForFrame(int timeout)
    {
        rs2_error error = new rs2_error();
        rs2_frame frame = rs2_wait_for_frame(instance, timeout, error);
        checkError(error);
        return new Frame(frame);
    }



    @Override
    public void release() {
        rs2_delete_frame_queue(instance);
    }
}
