package org.intel.rs.api;
import org.bytedeco.javacpp.BytePointer;
import org.intel.rs.RealSenseException;

import static org.intel.rs.api.RealSense.*;

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
}
