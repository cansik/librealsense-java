package org.intel.rs.frame;

import org.bytedeco.librealsense2.rs2_frame;
import org.bytedeco.librealsense2.rs2_vector;

public class MotionFrame extends Frame {
    public MotionFrame(rs2_frame instance) {
        super(instance);
    }

    public rs2_vector getMotionDataPointer() {
        return new rs2_vector(instance);
    }

    public float[] getMotionData() {
        rs2_vector raw = getMotionDataPointer();
        float[] data = new float[]{raw.x(), raw.y(), raw.z()};
        raw.releaseReference();
        return data;
    }
}
