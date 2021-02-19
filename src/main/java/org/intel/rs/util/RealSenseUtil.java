package org.intel.rs.util;

import org.bytedeco.librealsense2.rs2_error;

import static org.bytedeco.librealsense2.global.realsense2.rs2_get_error_message;

public final class RealSenseUtil {
    private RealSenseUtil() { }

    public static boolean toBoolean(int value) {
        return value >= 1;
    }

    public static boolean toBoolean(String value) {
        if(value == null)
            return false;

        return value.equals("YES");
    }
}
