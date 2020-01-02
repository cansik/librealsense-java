package org.intel.rs.processing;

import org.bytedeco.librealsense2.rs2_error;
import org.bytedeco.librealsense2.rs2_processing_block;
import org.intel.rs.frame.FrameQueue;
import org.intel.rs.frame.VideoFrame;

import java.util.function.Function;

import static org.intel.rs.util.RealSenseUtil.checkError;
import static org.bytedeco.librealsense2.global.realsense2.*;

public abstract class FilterProcessingBlock extends ProcessingBlock {

    private final FrameQueue queue = new FrameQueue(1);

    public FilterProcessingBlock(Function<rs2_error, rs2_processing_block> createFilterBlock) {
        rs2_error error = new rs2_error();
        instance = createFilterBlock.apply(error);
        checkError(error);

        rs2_start_processing_queue(instance, queue.getInstance(), error);
        checkError(error);
    }

    public VideoFrame applyFilter(VideoFrame original)
    {
        rs2_error error = new rs2_error();
        rs2_frame_add_ref(original.getInstance(), error);
        checkError(error);

        rs2_process_frame(instance, original.getInstance(), error);
        checkError(error);

        return (VideoFrame)queue.waitForFrame();
    }
}
