package org.intel.rs.processing;

import org.intel.rs.frame.Frame;

public interface FrameCallback {
    void process(Frame frame);
}
