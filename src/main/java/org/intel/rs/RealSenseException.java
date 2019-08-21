package org.intel.rs;

import static org.intel.rs.api.RealSense.*;

public class RealSenseException extends Exception {
    private rs2_error error;
    private String message;

    public RealSenseException(rs2_error error, String message) {
        this.error = error;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
