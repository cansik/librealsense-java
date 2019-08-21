package org.intel.rs;

import static org.intel.rs.api.RealSense.*;
import static org.intel.rs.api.RealSenseUtil.*;

public class StreamProfile implements AutoCloseable {
    rs2_stream_profile instance;

    public StreamProfile(rs2_stream_profile instance)
    {
        this.instance = instance;
    }

    @Override
    public void close() throws Exception {
        rs2_delete_stream_profile(instance);
    }
}
