package org.intel.rs;

import static org.intel.rs.api.RealSense.*;
import static org.intel.rs.api.RealSenseUtil.*;

public class FrameList extends Frame {

    public FrameList(rs2_frame instance) {
        this.instance = instance;
    }

    public int count() {
        rs2_error error = new rs2_error();
        int count = rs2_embedded_frames_count(instance, error);
        checkError(error);
        return count;
    }

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
}
