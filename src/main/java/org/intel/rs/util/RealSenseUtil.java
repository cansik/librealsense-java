package org.intel.rs.util;
import org.bytedeco.javacpp.Pointer;
import org.intel.rs.util.RealSenseException;

import static org.bytedeco.librealsense2.global.realsense2.*;
import org.bytedeco.librealsense2.*;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

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

    public static ByteBuffer newDirectByteBuffer(Pointer ptr, int size) {
        try {
            Field address = Buffer.class.getDeclaredField("address");
            address.setAccessible(true);
            Field capacity = Buffer.class.getDeclaredField("capacity");
            capacity.setAccessible(true);

            ByteBuffer bb = ByteBuffer.allocateDirect(0).order(ByteOrder.nativeOrder());
            address.setLong(bb, ptr.address());
            capacity.setInt(bb, size);

            return bb;
        } catch (Exception ex) {
            return null;
        }
    }
}
