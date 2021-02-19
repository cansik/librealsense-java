package org.intel.rs.frame;

import org.bytedeco.librealsense2.rs2_error;
import org.bytedeco.librealsense2.rs2_frame;
import org.intel.rs.stream.StreamProfile;
import org.intel.rs.types.Extension;
import org.intel.rs.types.Format;
import org.intel.rs.types.Stream;
import org.intel.rs.util.NativeList;
import org.intel.rs.util.NativeListIterator;

import java.util.Iterator;

import static org.bytedeco.librealsense2.global.realsense2.*;
import org.intel.rs.util.RealSenseError;

public class FrameList extends Frame implements NativeList<Frame> {

    public FrameList(rs2_frame instance) {
        super(instance);
    }

    @Override
    public int count() {
        int count = rs2_embedded_frames_count(instance, RealSenseError.getInstance());
        RealSenseError.checkError();
        return count;
    }

    @Override
    public Frame get(int index) {
        rs2_frame frame = rs2_extract_frame(instance, index, RealSenseError.getInstance());
        RealSenseError.checkError();
        return createFrame(frame);
    }

    public Frame asFrame() {
        rs2_frame_add_ref(instance, RealSenseError.getInstance());
        RealSenseError.checkError();

        return new Frame(instance);
    }

    public static FrameList fromFrame(Frame composite) throws Exception {
        if (composite.isExtendableTo(Extension.CompositeFrame)) {
            rs2_frame_add_ref(composite.getInstance(), RealSenseError.getInstance());
            RealSenseError.checkError();
            return new FrameList(composite.getInstance());
        }
        throw new Exception("The frame is a not composite frame");
    }

    public static Frame createFrame(rs2_frame ptr) {
        // todo: currently no error checking
        if (rs2_is_frame_extendable_to(ptr, Extension.Points.getIndex(), RealSenseError.getInstance()) > 0)
            return new Points(ptr);

        if (rs2_is_frame_extendable_to(ptr, Extension.DepthFrame.getIndex(), RealSenseError.getInstance()) > 0)
            return new DepthFrame(ptr);

        if (rs2_is_frame_extendable_to(ptr, Extension.VideoFrame.getIndex(), RealSenseError.getInstance()) > 0)
            return new VideoFrame(ptr);

        return new Frame(ptr);
    }

    public <T extends Frame> T getFirstOrDefault(Stream stream) {
        return getFirstOrDefault(stream, Format.Any);
    }

    public <T extends Frame> T getFirstOrDefault(Stream stream, Format format) {
        for (Frame frame : this) {
            StreamProfile profile = frame.getProfile();
            if (profile.getStream() == stream
                    && (Format.Any == format || profile.getFormat() == format)) {
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

    public PoseFrame getPoseFrame() {
        return getFirstOrDefault(Stream.Pose);
    }

    public MotionFrame getGyroscopeFrame() {
        return getFirstOrDefault(Stream.Gyro, Format.Xyz32f);
    }

    public MotionFrame getAccelerometerFrame() {
        return getFirstOrDefault(Stream.Accel, Format.Xyz32f);
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
