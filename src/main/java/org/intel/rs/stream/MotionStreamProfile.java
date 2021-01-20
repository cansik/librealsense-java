package org.intel.rs.stream;

import org.bytedeco.librealsense2.rs2_error;
import org.bytedeco.librealsense2.rs2_motion_device_intrinsic;
import org.bytedeco.librealsense2.rs2_stream_profile;
import org.intel.rs.types.MotionDeviceIntrinsic;

import static org.bytedeco.librealsense2.global.realsense2.rs2_get_extrinsics;
import static org.bytedeco.librealsense2.global.realsense2.rs2_get_motion_intrinsics;
import static org.intel.rs.util.RealSenseUtil.checkError;

public class MotionStreamProfile  extends StreamProfile{
    public MotionStreamProfile(rs2_stream_profile instance) {
        super(instance);
    }

    public MotionDeviceIntrinsic getIntrinsics()
    {
        rs2_error error = new rs2_error();
        rs2_motion_device_intrinsic intrinsics = new rs2_motion_device_intrinsic(1);
        rs2_get_motion_intrinsics(instance, intrinsics, error);
        checkError(error);

        return new MotionDeviceIntrinsic(intrinsics);
    }
}
