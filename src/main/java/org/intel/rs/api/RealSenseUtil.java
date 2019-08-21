package org.intel.rs.api;
import org.bytedeco.javacpp.BytePointer;
import org.intel.rs.RealSenseException;

import static org.intel.rs.api.RealSense.*;

public class RealSenseUtil {
    public static boolean errorFlag = false;

    public static void checkError(rs2_error error) {
        if(error.isNull()) {
            errorFlag = false;
            return;
        }

        String msg = rs2_get_error_message(error).getString();
        System.err.println("RealSense Error: " + msg);
        errorFlag = true;

        // todo: create exception an throw it here!
        //throw new RealSenseException(error, msg);
    }

    public static boolean toBoolean(int value) {
        return value >= 1;
    }
}
