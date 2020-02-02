package org.intel.rs.processing;

import org.bytedeco.librealsense2.rs2_error;
import org.intel.rs.frame.Frame;
import org.intel.rs.frame.FrameQueue;
import org.intel.rs.frame.Points;
import org.intel.rs.frame.VideoFrame;
import org.intel.rs.types.Option;

import static org.bytedeco.librealsense2.global.realsense2.*;
import static org.intel.rs.util.RealSenseUtil.checkError;

public class PointCloud extends ProcessingBlock {
    private final FrameQueue queue = new FrameQueue(1);

    public PointCloud() {
        rs2_error error = new rs2_error();
        instance = rs2_create_pointcloud(error);
        checkError(error);

        rs2_start_processing_queue(instance, queue.getInstance(), error);
        checkError(error);
    }

    public Points calculate(Frame original) {
        rs2_error error = new rs2_error();
        rs2_frame_add_ref(original.getInstance(), error);
        checkError(error);

        rs2_process_frame(instance, original.getInstance(), error);
        return (Points) queue.waitForFrame();
    }

    public void mapTexture(VideoFrame texture) {
        rs2_error error = new rs2_error();

        // use getOptions to call lazy initializer
        getOptions().get(Option.StreamFilter).setValue(texture.getProfile().getStream().getIndex());
        getOptions().get(Option.StreamFormatFilter).setValue(texture.getProfile().getFormat().getIndex());
        getOptions().get(Option.StreamIndexFilter).setValue(texture.getProfile().getIndex());

        rs2_frame_add_ref(texture.getInstance(), error);
        checkError(error);

        rs2_process_frame(instance, texture.getInstance(), error);
        checkError(error);
    }
}
