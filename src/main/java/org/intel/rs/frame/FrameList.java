package org.intel.rs.frame;

import static org.bytedeco.librealsense2.global.realsense2.*;
import org.bytedeco.librealsense2.*;
import org.intel.rs.util.NativeList;
import org.intel.rs.util.NativeListIterator;

import java.util.Iterator;

import static org.intel.rs.util.RealSenseUtil.*;

public class FrameList extends Frame implements NativeList<Frame> {

    public FrameList(rs2_frame instance) {
        super(instance);
    }

    @Override
    public int count() {
        rs2_error error = new rs2_error();
        int count = rs2_embedded_frames_count(instance, error);
        checkError(error);
        return count;
    }

    @Override
    public Frame get(int index) {
        rs2_error error = new rs2_error();
        rs2_frame frame = rs2_extract_frame(instance, index, error);
        checkError(error);
        return new Frame(frame);
    }

    public Frame asFrame()
    {
        rs2_error error = new rs2_error();
        rs2_frame_add_ref(instance, error);
        checkError(error);

        return new Frame(instance);
    }

    public static FrameList fromFrame(Frame frame)
    {
        rs2_error error = new rs2_error();
        rs2_frame_add_ref(frame.instance, error);
        checkError(error);

        return new FrameList(frame.instance);
    }

    @Override
    public void release() {
        // todo: release all frames in list if necessary
        rs2_release_frame(instance);
    }

    @Override
    public Iterator<Frame> iterator() {
        return new NativeListIterator(this);
    }
}
