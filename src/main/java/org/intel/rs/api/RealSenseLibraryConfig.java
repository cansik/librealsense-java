package org.intel.rs.api;

import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(
        value = @Platform(
                includepath = {
                        "../../../librealsense/include/librealsense2"
                },
                include = {
                        "rs.h",
                        "rs_advanced_mode.h",
                        "rsutil.h",
                        "h/rs_advanced_mode_command.h",
                        "h/rs_config.h",
                        "h/rs_context.h",
                        "h/rs_device.h",
                        "h/rs_frame.h",
                        "h/rs_internal.h",
                        "h/rs_option.h",
                        "h/rs_pipeline.h",
                        "h/rs_processing.h",
                        "h/rs_record_playback.h",
                        "h/rs_sensor.h",
                        "h/rs_types.h",
                },
                linkpath = {
                        "/Users/cansik/git/private/librealsense-java/librealsense/build"
                        //"../../../librealsense/build"
                },
                link = {"realsense2"}
        ),
        target = "org.intel.rs.api.RealSense"
)

public class RealSenseLibraryConfig implements InfoMapper {
    public void map(InfoMap infoMap) {
        // RS2_API_VERSION_STR
        infoMap.put(new Info("RS2_API_VERSION_STR")
                .javaText("public static final String RS2_API_VERSION_STR = RS2_API_MAJOR_VERSION + \".\" " +
                        "+ RS2_API_MINOR_VERSION + \".\" + RS2_API_PATCH_VERSION;"));

        // typedef double fixes
        infoMap.put(new Info("rs2_time_t").valueTypes("double"));
        infoMap.put(new Info("rs2_metadata_type").valueTypes("double"));

        // typedef enum fixes
        // rs_frame.h
        infoMap.put(new Info("rs2_timestamp_domain").enumerate());
        infoMap.put(new Info("rs2_frame_metadata_value").enumerate());

        // rs_internal
        infoMap.put(new Info("rs2_recording_mode").enumerate());

        // rs_option.h
        infoMap.put(new Info("rs2_option").enumerate());
        infoMap.put(new Info("rs2_sr300_visual_preset").enumerate());

        // rs_record_playback.h
        infoMap.put(new Info("rs2_playback_status").enumerate());

        // rs_sensor.h
        infoMap.put(new Info("rs2_camera_info").enumerate());
        infoMap.put(new Info("rs2_stream").enumerate());
        infoMap.put(new Info("rs2_format").enumerate());

        // rs_types.h
        infoMap.put(new Info("rs2_notification_category").enumerate());
        infoMap.put(new Info("rs2_exception_type").enumerate());
        infoMap.put(new Info("rs2_distortion").enumerate());
        infoMap.put(new Info("rs2_log_severity").enumerate());
        infoMap.put(new Info("rs2_extension").enumerate());
        infoMap.put(new Info("rs2_matchers").enumerate());

        // bypointer
        //infoMap.put(new Info("::rs2_stream").annotations("@ByPtr"));
        infoMap.put(new Info("rs2_get_stream_profile_data").skip());
        infoMap.put(new Info("rs2_create_processing_block_fptr").skip());
        infoMap.put(new Info("rs2_start_processing_fptr").skip());
        infoMap.put(new Info("rs2_start_processing_fptr").skip());
        infoMap.put(new Info("rs2_start").skip());
        //infoMap.put(new Info("rs2_stream").pointerTypes("StreamPtr").define());
        //infoMap.put(new Info("rs2_get_stream_profile_data").javaText("public native void setCallback(@ByRef FuncBool callback);"));

        // remove callbacks (easier for the beginning)
        infoMap.put(new Info("rs2_devices_changed_callback_ptr").skip());
        infoMap.put(new Info("rs2_pipeline_start_with_callback").skip());
        infoMap.put(new Info("rs2_pipeline_start_with_config_and_callback").skip());
        infoMap.put(new Info("rs2_set_notifications_callback").skip());
    }
}