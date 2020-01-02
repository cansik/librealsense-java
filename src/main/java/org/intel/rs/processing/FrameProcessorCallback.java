package org.intel.rs.processing;

import org.intel.rs.frame.Frame;

public interface FrameProcessorCallback {
    void process(Frame frame, FrameSource source);
}
