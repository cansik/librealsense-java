package org.intel.rs.stream;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.librealsense2.rs2_error;
import org.bytedeco.librealsense2.rs2_extrinsics;
import org.bytedeco.librealsense2.rs2_stream_profile;
import org.intel.rs.types.Format;
import org.intel.rs.types.Stream;
import org.intel.rs.util.NativeDecorator;

import static org.bytedeco.librealsense2.global.realsense2.rs2_get_extrinsics;
import static org.bytedeco.librealsense2.global.realsense2.rs2_get_stream_profile_data;
import org.intel.rs.util.RealSenseError;

public class StreamProfile implements NativeDecorator<rs2_stream_profile> {
    rs2_stream_profile instance;

    private final int nativeStreamIndex;
    private final int nativeFormatIndex;
    private final int index;
    private final int uniqueId;
    private final int frameRate;

    // instantiate int-pointer here to avoid memory leak: problematic it is not thread-safe anymore!
    private static final IntPointer data = new IntPointer(5);
    private static final Object dataLock = new Object();

    public StreamProfile(rs2_stream_profile instance) {
        this.instance = instance;

        // load stream profile data
        synchronized (dataLock) {
            rs2_get_stream_profile_data(instance,
                    data.getPointer(0),
                    data.getPointer(1),
                    data.getPointer(2),
                    data.getPointer(3),
                    data.getPointer(4),
                    RealSenseError.getInstance());
            RealSenseError.checkError();

            nativeStreamIndex = data.get(0);
            nativeFormatIndex = data.get(1);
            index = data.get(2);
            uniqueId = data.get(3);
            frameRate = data.get(4);
        }
    }

    public rs2_extrinsics getExtrinsicsTo(StreamProfile other) {
        rs2_extrinsics extrinsics = new rs2_extrinsics(1);
        rs2_get_extrinsics(instance, other.getInstance(), extrinsics, RealSenseError.getInstance());
        RealSenseError.checkError();
        return extrinsics;
    }


    public int getNativeStreamIndex() {
        return nativeStreamIndex;
    }

    public Stream getStream() {
        return Stream.fromIndex(getNativeStreamIndex());
    }

    public int getNativeFormatIndex() {
        return nativeFormatIndex;
    }

    public Format getFormat() {
        return Format.fromIndex(getNativeFormatIndex());
    }

    public int getIndex() {
        return index;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public int getFrameRate() {
        return frameRate;
    }

    @Override
    public rs2_stream_profile getInstance() {
        return instance;
    }

    @Override
    public void release() {
        // warning: only use on a copy
        // rs2_delete_stream_profile(instance)
        instance.deallocate();
    }
}
