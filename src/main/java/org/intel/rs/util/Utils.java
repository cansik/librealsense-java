package org.intel.rs.util;

import org.bytedeco.javacpp.FloatPointer;
import org.intel.rs.types.Intrinsics;
import org.intel.rs.types.Pixel;
import org.intel.rs.types.Vertex;

import static org.bytedeco.librealsense2.global.realsense2.rs2_deproject_pixel_to_point;
import static org.bytedeco.librealsense2.global.realsense2.rs2_project_point_to_pixel;

public class Utils {

    public static Vertex deprojectPixelToPoint(final Intrinsics intrinsics, final Pixel pixel, final float depth) {
        FloatPointer point = new FloatPointer(3);
        FloatPointer pixelPtr = new FloatPointer(2);
        pixelPtr.put(0, pixel.getI());
        pixelPtr.put(1, pixel.getJ());
        rs2_deproject_pixel_to_point(point, intrinsics.getInstance(), pixelPtr, depth);

        return new Vertex(point.get(0), point.get(1), point.get(2));
    }

    public static Pixel projectPointToPixel(final Intrinsics intrinsics, final Vertex point) {
        FloatPointer pointPtr = new FloatPointer(3);
        FloatPointer pixel = new FloatPointer(2);

        pointPtr.put(point.getX(), point.getY(), point.getZ());
        rs2_project_point_to_pixel(pixel, intrinsics.getInstance(), pointPtr);

        return new Pixel((int)pixel.get(0), (int)pixel.get(1));
    }
}
