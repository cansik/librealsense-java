package org.intel.rs.frame;

import org.bytedeco.librealsense2.rs2_error;
import org.bytedeco.librealsense2.rs2_frame;
import org.bytedeco.librealsense2.rs2_pose;
import org.intel.rs.types.Pose;

import static org.bytedeco.librealsense2.global.realsense2.rs2_pose_frame_get_pose_data;
import static org.intel.rs.util.RealSenseUtil.checkError;

public class PoseFrame extends Frame {
    public PoseFrame(rs2_frame instance) {
        super(instance);
    }

    public rs2_pose getPoseData() {
        rs2_error error = new rs2_error();
        rs2_pose pose = new rs2_pose();
        rs2_pose_frame_get_pose_data(instance, pose, error);
        checkError(error);
        return pose;
    }

    public Pose getPose() {
        rs2_pose data = getPoseData();
        Pose pose = new Pose(data);
        data.releaseReference();
        return pose;
    }
}
