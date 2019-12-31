package org.intel.rs.stream;

import org.bytedeco.librealsense2.rs2_error;
import org.bytedeco.librealsense2.rs2_stream_profile;
import org.bytedeco.librealsense2.rs2_stream_profile_list;
import org.intel.rs.util.NativeDecorator;
import org.intel.rs.util.NativeList;
import org.intel.rs.util.NativeListIterator;

import java.util.Iterator;

import static org.bytedeco.librealsense2.global.realsense2.*;
import static org.intel.rs.util.RealSenseUtil.checkError;
import static org.intel.rs.util.RealSenseUtil.toBoolean;

public class StreamProfileList implements NativeDecorator<rs2_stream_profile_list>, NativeList<StreamProfile> {
    rs2_stream_profile_list instance;

    public StreamProfileList(rs2_stream_profile_list instance) {
        this.instance = instance;
    }

    @Override
    public StreamProfile get(int index) {
        rs2_error error = new rs2_error();
        rs2_stream_profile profile = rs2_get_stream_profile(instance, index, error);
        checkError(error);

        // check if profile is video
        boolean isVideoProfile = toBoolean(rs2_stream_profile_is(profile, RS2_EXTENSION_VIDEO, error));
        checkError(error);

        if(isVideoProfile) {
            return new VideoStreamProfile(profile);
        }

        return new StreamProfile(profile);
    }

    @Override
    public int count() {
        rs2_error error = new rs2_error();
        int count = rs2_get_stream_profiles_count(instance, error);
        checkError(error);
        return count;
    }

    @Override
    public rs2_stream_profile_list getInstance() {
        return instance;
    }

    @Override
    public void release() {
        rs2_delete_stream_profiles_list(instance);
    }

    @Override
    public Iterator<StreamProfile> iterator() {
        return new NativeListIterator<>(this);
    }
}
