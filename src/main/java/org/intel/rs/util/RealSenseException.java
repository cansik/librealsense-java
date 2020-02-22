package org.intel.rs.util;

import org.bytedeco.librealsense2.rs2_error;

public class RealSenseException extends RuntimeException {
    private rs2_error error;

    public RealSenseException(rs2_error error, String message) {
        super(message);
        this.error = error;
    }

    public rs2_error getError() {
        return error;
    }
}
