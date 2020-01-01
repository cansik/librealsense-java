package org.intel.rs.util;

import org.bytedeco.librealsense2.rs2_error;

import static org.bytedeco.librealsense2.global.realsense2.rs2_get_error_message;

public class RealSenseUtil {
    public static void checkError(rs2_error error) {
        if(error.isNull())
            return;

        String msg = rs2_get_error_message(error).getString();
        throw new RealSenseException(error, msg);
    }

    public static boolean toBoolean(int value) {
        return value >= 1;
    }

    public static boolean toBoolean(String value) {
        if(value == null)
            return false;

        return value.equals("YES");
    }
}
