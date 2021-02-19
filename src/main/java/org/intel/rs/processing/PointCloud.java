package org.intel.rs.processing;

import org.bytedeco.librealsense2.rs2_error;
import org.intel.rs.frame.Frame;
import org.intel.rs.frame.FrameQueue;
import org.intel.rs.frame.Points;
import org.intel.rs.frame.VideoFrame;
import org.intel.rs.types.Option;

import static org.bytedeco.librealsense2.global.realsense2.*;
import org.intel.rs.util.RealSenseError;

public class PointCloud extends ProcessingBlock {
    private final FrameQueue queue = new FrameQueue(1);

    public PointCloud() {
        instance = rs2_create_pointcloud(RealSenseError.getInstance());
        RealSenseError.checkError();

        rs2_start_processing_queue(instance, queue.getInstance(), RealSenseError.getInstance());
        RealSenseError.checkError();
    }

    public Points calculate(Frame original) {
        rs2_frame_add_ref(original.getInstance(), RealSenseError.getInstance());
        RealSenseError.checkError();

        rs2_process_frame(instance, original.getInstance(), RealSenseError.getInstance());
        return (Points) queue.waitForFrame();
    }

    public void mapTexture(VideoFrame texture) {

        // use getOptions to call lazy initializer
        getOptions().get(Option.StreamFilter).setValue(texture.getProfile().getStream().getIndex());
        getOptions().get(Option.StreamFormatFilter).setValue(texture.getProfile().getFormat().getIndex());
        getOptions().get(Option.StreamIndexFilter).setValue(texture.getProfile().getIndex());

        rs2_frame_add_ref(texture.getInstance(), RealSenseError.getInstance());
        RealSenseError.checkError();

        rs2_process_frame(instance, texture.getInstance(), RealSenseError.getInstance());
        RealSenseError.checkError();
    }
}
