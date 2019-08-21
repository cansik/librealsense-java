package org.intel.rs;

import static org.intel.rs.api.RealSense.*;
import static org.intel.rs.api.RealSenseUtil.*;

public class FrameQueue implements AutoCloseable  {
    rs2_frame_queue instance;

    public FrameQueue(rs2_frame_queue instance) {
        this.instance = instance;
    }

    @Override
    public void close() throws Exception {
        rs2_delete_frame_queue(instance);
    }
}
