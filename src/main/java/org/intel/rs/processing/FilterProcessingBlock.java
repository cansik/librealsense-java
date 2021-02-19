package org.intel.rs.processing;

import org.bytedeco.librealsense2.rs2_error;
import org.bytedeco.librealsense2.rs2_processing_block;
import org.intel.rs.frame.Frame;
import org.intel.rs.frame.FrameQueue;
import org.intel.rs.frame.VideoFrame;

import java.util.function.Function;

import org.intel.rs.util.RealSenseError;
import static org.bytedeco.librealsense2.global.realsense2.*;

public abstract class FilterProcessingBlock extends ProcessingBlock {

    private final FrameQueue queue = new FrameQueue(1);

    public FilterProcessingBlock(Function<rs2_error, rs2_processing_block> createFilterBlock) {
        instance = createFilterBlock.apply(RealSenseError.getInstance());
        RealSenseError.checkError();

        rs2_start_processing_queue(instance, queue.getInstance(), RealSenseError.getInstance());
        RealSenseError.checkError();
    }

    public <T extends Frame> T process(VideoFrame original) {
        rs2_frame_add_ref(original.getInstance(), RealSenseError.getInstance());
        RealSenseError.checkError();

        rs2_process_frame(instance, original.getInstance(), RealSenseError.getInstance());
        RealSenseError.checkError();

        return (T) queue.waitForFrame();
    }
}
