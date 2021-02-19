package org.intel.rs.stream;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.librealsense2.rs2_error;
import org.bytedeco.librealsense2.rs2_extrinsics;
import org.bytedeco.librealsense2.rs2_stream_profile;
import org.intel.rs.types.Format;
import org.intel.rs.types.Stream;
import org.intel.rs.util.NativeDecorator;
import org.intel.rs.util.RealSenseError;

import static org.bytedeco.librealsense2.global.realsense2.rs2_get_extrinsics;
import static org.bytedeco.librealsense2.global.realsense2.rs2_get_stream_profile_data;
import static org.intel.rs.util.RealSenseError.checkError;

public class StreamProfile implements NativeDecorator<rs2_stream_profile> {
    rs2_stream_profile instance;

    private final int nativeStreamIndex;
    private final int nativeFormatIndex;
    private final int index;
    private final int uniqueId;
    private final int frameRate;

    // instantiate int-pointer per thread here to avoid memory leak
    private static final ThreadLocal<IntPointer> nativeStreamIndexPtr = ThreadLocal.withInitial(() -> new IntPointer(1));
    private static final ThreadLocal<IntPointer> nativeFormatIndexPtr = ThreadLocal.withInitial(() -> new IntPointer(1));
    private static final ThreadLocal<IntPointer> indexPtr = ThreadLocal.withInitial(() -> new IntPointer(1));
    private static final ThreadLocal<IntPointer> uniqueIdPtr = ThreadLocal.withInitial(() -> new IntPointer(1));
    private static final ThreadLocal<IntPointer> frameRatePtr = ThreadLocal.withInitial(() -> new IntPointer(1));

    public StreamProfile(rs2_stream_profile instance) {
        this.instance = instance;

        // load stream profile data
        rs2_get_stream_profile_data(instance,
                nativeStreamIndexPtr.get(),
                nativeFormatIndexPtr.get(),
                indexPtr.get(),
                uniqueIdPtr.get(),
                frameRatePtr.get(),
                RealSenseError.getInstance());
        RealSenseError.checkError();

        nativeStreamIndex = nativeStreamIndexPtr.get().get(0);
        nativeFormatIndex = nativeFormatIndexPtr.get().get(0);
        index = indexPtr.get().get(0);
        uniqueId = uniqueIdPtr.get().get(0);
        frameRate = frameRatePtr.get().get(0);
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
    }
}
