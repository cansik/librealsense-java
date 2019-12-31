package org.intel.rs.frame;

import static org.bytedeco.librealsense2.global.realsense2.*;

import org.bytedeco.librealsense2.*;
import org.intel.rs.types.Extension;
import org.intel.rs.types.Format;
import org.intel.rs.types.Stream;
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
        return createFrame(frame);
    }

    public Frame asFrame() {
        rs2_error error = new rs2_error();
        rs2_frame_add_ref(instance, error);
        checkError(error);

        return new Frame(instance);
    }

    public static FrameList fromFrame(Frame composite) throws Exception {
        rs2_error error = new rs2_error();
        if (composite.isExtendableTo(Extension.CompositeFrame)) {
            rs2_frame_add_ref(composite.getInstance(), error);
            checkError(error);
            return new FrameList(composite.getInstance());
        }
        throw new Exception("The frame is a not composite frame");
    }

    public static Frame createFrame(rs2_frame ptr) {
        // todo: currently no error checking
        rs2_error error = new rs2_error();
        if (rs2_is_frame_extendable_to(ptr, Extension.Points.getIndex(), error) > 0)
            return new Points(ptr);

        if (rs2_is_frame_extendable_to(ptr, Extension.DepthFrame.getIndex(), error) > 0)
            return new DepthFrame(ptr);

        if (rs2_is_frame_extendable_to(ptr, Extension.VideoFrame.getIndex(), error) > 0)
            return new VideoFrame(ptr);

        return new Frame(ptr);
    }

    public <T extends Frame> T getFirstOrDefault(Stream stream) {
        return getFirstOrDefault(stream, Format.Any);
    }

    public <T extends Frame> T getFirstOrDefault(Stream stream, Format format) {
        for (Frame frame : this) {
            if (frame.getProfile().getStream() == stream
                    && (Format.Any == format || frame.getProfile().getFormat() == format)) {
                return (T) frame;
            }
            frame.release();
        }
        return null;
    }

    public DepthFrame getDepthFrame() {
        return getFirstOrDefault(Stream.Depth);
    }

    public VideoFrame getColorFrame() {
        return getFirstOrDefault(Stream.Color);
    }

    @Override
    public void release() {
        // todo: release all frames in list if necessary
        rs2_release_frame(instance);
    }

    @Override
    public Iterator<Frame> iterator() {
        return new NativeListIterator<>(this);
    }
}
