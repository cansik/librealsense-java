package org.intel.rs.stream;

import org.bytedeco.javacpp.IntPointer;
import org.intel.rs.types.Format;
import org.intel.rs.types.Stream;
import org.intel.rs.util.NativeDecorator;

import static org.bytedeco.librealsense2.global.realsense2.*;
import static org.intel.rs.util.RealSenseUtil.checkError;

import org.bytedeco.librealsense2.*;

public class StreamProfile implements NativeDecorator<rs2_stream_profile> {
    rs2_stream_profile instance;

    private IntPointer nativeStreamIndex = new IntPointer(1);
    private IntPointer nativeFormatIndex = new IntPointer(1);
    private IntPointer index = new IntPointer(1);
    private IntPointer uniqueId = new IntPointer(1);
    private IntPointer frameRate = new IntPointer(1);

    public StreamProfile(rs2_stream_profile instance)
    {
        this.instance = instance;

        // load stream profile data
        rs2_error error = new rs2_error();
        rs2_get_stream_profile_data(instance,
                nativeStreamIndex,
                nativeFormatIndex,
                index,
                uniqueId,
                frameRate,
                error);
        checkError(error);
    }

    // todo: implement to get extrinsics


    public int getNativeStreamIndex() {
        return nativeStreamIndex.get();
    }

    public Stream getStream() {
        return Stream.fromIndex(getNativeStreamIndex());
    }

    public int getNativeFormatIndex() {
        return nativeFormatIndex.get();
    }

    public Format getFormat() {
        return Format.fromIndex(getNativeFormatIndex());
    }

    public int getIndex() {
        return index.get();
    }

    public int getUniqueId() {
        return uniqueId.get();
    }

    public int getFrameRate() {
        return frameRate.get();
    }

    @Override
    public rs2_stream_profile getInstance() {
        return instance;
    }

    @Override
    public void release() {
        rs2_delete_stream_profile(instance);
    }
}
