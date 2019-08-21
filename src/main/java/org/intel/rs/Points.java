package org.intel.rs;

import static org.intel.rs.api.RealSense.*;
import static org.intel.rs.api.RealSenseUtil.*;
import static org.intel.rs.api.RealSenseUtil.checkError;

public class Points extends Frame {

    public Points(rs2_frame instance) {
        super(instance);
    }

    public int count() {
        rs2_error error = new rs2_error();
        int count = rs2_get_frame_points_count(instance, error);
        checkError(error);
        return count;
    }

    public rs2_vertex getVertices() {
        // todo: create method with java array as output
        rs2_error error = new rs2_error();
        rs2_vertex vertexPtr = rs2_get_frame_vertices(instance, error);
        checkError(error);
        return vertexPtr;
    }

    public rs2_pixel getTextureCoordinates() {
        // todo: create method with java array as output
        rs2_error error = new rs2_error();
        rs2_pixel pixelPtr = rs2_get_frame_texture_coordinates(instance, error);
        checkError(error);
        return pixelPtr;
    }

    public void exportToPly(String fileName, Frame texture) {
        rs2_error error = new rs2_error();
        rs2_export_to_ply(instance, fileName, texture.instance, error);
        checkError(error);
    }
}
