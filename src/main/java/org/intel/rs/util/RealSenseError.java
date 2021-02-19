package org.intel.rs.util;

import org.bytedeco.librealsense2.rs2_error;

import static org.bytedeco.librealsense2.global.realsense2.rs2_get_error_message;

public final class RealSenseError {
    private RealSenseError() {}

    private static ThreadLocal<rs2_error> _threadLocalRS2Error = ThreadLocal.withInitial(rs2_error::new);

    public static rs2_error getInstance() {
        return _threadLocalRS2Error.get();
    }

    public static void checkError() {
        checkError(_threadLocalRS2Error.get());
    }

    public static void checkError(rs2_error error) {
        if(error.isNull()) {
            return;
        }

        String msg = rs2_get_error_message(RealSenseError.getInstance()).getString();
        throw new RealSenseException(error, msg);
    }
}
