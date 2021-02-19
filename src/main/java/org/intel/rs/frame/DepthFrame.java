package org.intel.rs.frame;

import org.bytedeco.librealsense2.rs2_error;
import org.bytedeco.librealsense2.rs2_frame;

import static org.bytedeco.librealsense2.global.realsense2.rs2_depth_frame_get_distance;
import org.intel.rs.util.RealSenseError;

public class DepthFrame extends VideoFrame {
    public DepthFrame(rs2_frame instance) {
        super(instance);
    }

    public float getDistance(int x, int y)
    {
        float distance = rs2_depth_frame_get_distance(instance, x, y, RealSenseError.getInstance());
        RealSenseError.checkError();
        return distance;
    }
}
