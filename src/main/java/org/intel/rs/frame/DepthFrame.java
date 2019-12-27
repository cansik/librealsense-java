package org.intel.rs.frame;

import org.bytedeco.librealsense2.rs2_error;
import org.bytedeco.librealsense2.rs2_frame;

import static org.bytedeco.librealsense2.global.realsense2.rs2_depth_frame_get_distance;
import static org.intel.rs.util.RealSenseUtil.checkError;

public class DepthFrame extends VideoFrame {
    public DepthFrame(rs2_frame instance) {
        super(instance);
    }

    public float getDistance(int x, int y)
    {
        rs2_error error = new rs2_error();
        float distance = rs2_depth_frame_get_distance(instance, x, y, error);
        checkError(error);
        return distance;
    }
}
