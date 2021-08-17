package org.intel.rs.util;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.librealsense2.rs2_intrinsics;
import org.intel.rs.types.Pixel;
import org.intel.rs.types.Vertex;

import java.nio.FloatBuffer;

import static org.bytedeco.librealsense2.global.realsense2.rs2_deproject_pixel_to_point;

public class Utils {

    // todo: fix this method to only use higher types
    public static Vertex deprojectPixelToPoint(final rs2_intrinsics intrinsic, final Pixel pixel, final float depth) {
        FloatPointer point = new FloatPointer(3);
        FloatPointer pixelPtr = new FloatPointer(2);

        pixelPtr.put(pixel.getI(), pixel.getJ());
        rs2_deproject_pixel_to_point(point, intrinsic, pixelPtr, depth);

        return new Vertex(point.get(0), point.get(1), point.get(2));
    }
}
