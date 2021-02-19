package org.intel.rs.stream;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.librealsense2.rs2_intrinsics;
import org.bytedeco.librealsense2.rs2_stream_profile;
import org.intel.rs.util.RealSenseError;

import static org.bytedeco.librealsense2.global.realsense2.rs2_get_video_stream_intrinsics;
import static org.bytedeco.librealsense2.global.realsense2.rs2_get_video_stream_resolution;

public class VideoStreamProfile extends StreamProfile {
    private int width;
    private int height;

    public VideoStreamProfile(rs2_stream_profile instance) {
        super(instance);

        IntPointer widthPtr = new IntPointer(1);
        IntPointer heightPtr = new IntPointer(1);
        rs2_get_video_stream_resolution(instance, widthPtr, heightPtr, RealSenseError.getInstance());
        RealSenseError.checkError();

        this.width = widthPtr.get();
        this.height = heightPtr.get();
    }

    public rs2_intrinsics getIntrinsics() {
        rs2_intrinsics intrinsics = new rs2_intrinsics(1);
        rs2_get_video_stream_intrinsics(instance, intrinsics, RealSenseError.getInstance());
        RealSenseError.checkError();
        return intrinsics;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
