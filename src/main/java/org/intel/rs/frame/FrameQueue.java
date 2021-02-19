package org.intel.rs.frame;

import org.intel.rs.util.NativeDecorator;

import static org.bytedeco.librealsense2.global.realsense2.*;
import org.bytedeco.librealsense2.*;
import org.intel.rs.util.RealSenseError;
import static org.intel.rs.util.RealSenseUtil.toBoolean;

public class FrameQueue implements NativeDecorator<rs2_frame_queue> {
    rs2_frame_queue instance;

    public FrameQueue() {
        this(1);
    }

    public FrameQueue(int capacity) {
        instance = rs2_create_frame_queue(capacity, RealSenseError.getInstance());
        RealSenseError.checkError();
    }

    public boolean pollForFrame(Frame frame)
    {
        int res = rs2_poll_for_frame(instance, frame.instance, RealSenseError.getInstance());
        RealSenseError.checkError();

        return toBoolean(res);
    }

    public Frame waitForFrame() {
        return waitForFrame(5000);
    }

    public Frame waitForFrame(int timeout)
    {
        rs2_frame frame = rs2_wait_for_frame(instance, timeout, RealSenseError.getInstance());
        RealSenseError.checkError();
        return FrameList.createFrame(frame);
    }

    public FrameList waitForFrames() {
        return waitForFrames(5000);
    }

    public FrameList waitForFrames(int timeout)
    {
        rs2_frame frame = rs2_wait_for_frame(instance, timeout, RealSenseError.getInstance());
        RealSenseError.checkError();
        return new FrameList(frame);
    }

    public void enqueue(Frame frame)
    {
        rs2_frame_add_ref(frame.instance, RealSenseError.getInstance());
        RealSenseError.checkError();

        rs2_enqueue_frame(frame.instance, instance);
    }

    @Override
    public rs2_frame_queue getInstance() {
        return instance;
    }

    @Override
    public void release() {
        rs2_delete_frame_queue(instance);
    }
}
