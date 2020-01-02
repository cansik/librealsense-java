package org.intel.rs.processing;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.librealsense2.*;
import org.intel.rs.frame.Frame;
import org.intel.rs.frame.FrameList;
import org.intel.rs.frame.FrameQueue;

import static org.bytedeco.librealsense2.global.realsense2.*;
import static org.intel.rs.util.RealSenseUtil.checkError;

public class CustomProcessingBlock extends ProcessingBlock {

    private rs2_frame_callback_ptr callback = null;
    private rs2_frame_processor_callback_ptr proc_callback = null;
    private FrameQueue queue = null;

    public CustomProcessingBlock(FrameProcessorCallback cb) {
        rs2_error error = new rs2_error();

        proc_callback = new rs2_frame_processor_callback_ptr() {
            @Override
            public void call(rs2_frame framePtr, rs2_source src, Pointer u) {
                // todo: check for memory leak
                Frame frame = new Frame(framePtr);
                cb.process(frame, new FrameSource(src));
            }
        };
        instance = rs2_create_processing_block_fptr(proc_callback, null, error);
        checkError(error);
    }

    public void processFrame(Frame frame) {
        rs2_error error = new rs2_error();
        rs2_frame_add_ref(frame.getInstance(), error);
        checkError(error);

        rs2_process_frame(instance, frame.getInstance(), error);
        checkError(error);
    }

    public void processFrames(FrameList frameList) {
        // todo: check for memory leak because of as frame
        processFrame(frameList.asFrame());
    }

    public void start(FrameQueue queue) {
        rs2_error error = new rs2_error();
        rs2_start_processing_queue(instance, queue.getInstance(), error);
        checkError(error);

        callback = null;
        this.queue = queue;
    }

    public void start(FrameCallback cb) {
        rs2_error error = new rs2_error();

        callback = new rs2_frame_callback_ptr() {
            @Override
            public void call(rs2_frame framePtr, Pointer u) {
                // todo: check for memory leak
                Frame frame = new Frame(framePtr);
                cb.process(frame);
            }
        };

        rs2_start_processing_fptr(instance, callback, null, error);
        checkError(error);
        this.queue = null;
    }
}
