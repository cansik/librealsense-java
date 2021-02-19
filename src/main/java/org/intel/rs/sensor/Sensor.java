package org.intel.rs.sensor;

import org.bytedeco.librealsense2.rs2_sensor;
import org.bytedeco.librealsense2.rs2_stream_profile;
import org.intel.rs.frame.FrameQueue;
import org.intel.rs.option.SensorOptions;
import org.intel.rs.stream.StreamProfile;
import org.intel.rs.stream.StreamProfileList;
import org.intel.rs.stream.VideoStreamProfile;
import org.intel.rs.types.CameraInfo;
import org.intel.rs.types.Extension;
import org.intel.rs.util.NativeDecorator;
import org.intel.rs.util.RealSenseError;

import java.util.ArrayList;
import java.util.List;

import static org.bytedeco.librealsense2.global.realsense2.*;
import static org.intel.rs.util.RealSenseUtil.toBoolean;

public class Sensor implements NativeDecorator<rs2_sensor> {
    rs2_sensor instance;
    private SensorOptions options;

    public Sensor(rs2_sensor instance) {
        this.instance = instance;
    }

    //region Sensor Info
    public String getName() {
        return getInfo(RS2_CAMERA_INFO_NAME);
    }

    public String getInfo(CameraInfo info) {
        return getInfo(info.getIndex());
    }

    public String getInfo(int info) {
        // check if info is supported
        boolean isSupported = toBoolean(rs2_supports_sensor_info(instance, info, RealSenseError.getInstance()));
        RealSenseError.checkError();

        if (!isSupported)
            return null;

        // read device info
        String infoText = rs2_get_sensor_info(instance, info, RealSenseError.getInstance()).getString();
        RealSenseError.checkError();

        return infoText;
    }
    //endregion

    @Override
    public rs2_sensor getInstance() {
        return instance;
    }

    @Override
    public void release() {
        rs2_delete_sensor(instance);
    }

    // todo: implement sensor roi settings (AutoExposureSettings)

    public SensorOptions getSensorOptions() {
        // todo: make this thread safe!
        if (options == null)
            options = new SensorOptions(instance);

        return options;
    }

    //region Sensor Stream Commands
    public void open(StreamProfile streamProfile) {
        rs2_open(instance, streamProfile.getInstance(), RealSenseError.getInstance());
        RealSenseError.checkError();
    }

    public void open(StreamProfile[] streamProfiles) {
        rs2_stream_profile[] nativeProfiles = new rs2_stream_profile[streamProfiles.length];
        for (int i = 0; i < streamProfiles.length; i++) {
            nativeProfiles[i] = streamProfiles[i].getInstance();
        }

        // todo: test this code!
        rs2_stream_profile arrayPointer = nativeProfiles[0];
        rs2_open_multiple(instance, arrayPointer, nativeProfiles.length, RealSenseError.getInstance());
        RealSenseError.checkError();
    }

    public void start(FrameQueue queue) {
        rs2_start_queue(instance, queue.getInstance(), RealSenseError.getInstance());
        RealSenseError.checkError();
    }

    // todo: implement start with FrameCallback

    public void stop() {
        rs2_close(instance, RealSenseError.getInstance());
        RealSenseError.checkError();
    }

    public void closeSensor() {
        rs2_close(instance, RealSenseError.getInstance());
        RealSenseError.checkError();
    }
    //endregion

    public float getDepthScale() {
        float depthScale = rs2_get_depth_scale(instance, RealSenseError.getInstance());
        RealSenseError.checkError();

        return depthScale;
    }

    public StreamProfileList getStreamProfiles() {
        StreamProfileList list = new StreamProfileList(rs2_get_stream_profiles(instance, RealSenseError.getInstance()));
        RealSenseError.checkError();
        return list;
    }


    public List<VideoStreamProfile> getVideoStreamProfiles() {
        List<VideoStreamProfile> videoProfiles = new ArrayList<VideoStreamProfile>();
        for (StreamProfile profile : getStreamProfiles()) {
            if (profile instanceof VideoStreamProfile)
                videoProfiles.add((VideoStreamProfile) profile);
        }
        return videoProfiles;
    }

    public boolean isExtendableTo(Extension extension) {
        int result = rs2_is_sensor_extendable_to(instance, extension.getIndex(), RealSenseError.getInstance());
        RealSenseError.checkError();
        return toBoolean(result);
    }
}