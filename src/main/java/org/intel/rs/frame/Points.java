package org.intel.rs.frame;

import static org.bytedeco.librealsense2.global.realsense2.*;

import org.bytedeco.librealsense2.*;
import org.intel.rs.types.Pixel;
import org.intel.rs.types.Vertex;

import org.intel.rs.util.RealSenseError;

public class Points extends Frame {

    public Points(rs2_frame instance) {
        super(instance);
    }

    public int count() {
        int count = rs2_get_frame_points_count(instance, RealSenseError.getInstance());
        RealSenseError.checkError();
        return count;
    }

    public rs2_vertex getVertexData() {
        // todo: create method with java array as output
        rs2_vertex vertexPtr = rs2_get_frame_vertices(instance, RealSenseError.getInstance());
        RealSenseError.checkError();
        return vertexPtr;
    }

    public Vertex[] getVertices() {
        // todo: speed this up -> take a look at c#
        int length = count();
        rs2_vertex data = getVertexData();
        Vertex[] vertices = new Vertex[length];

        for (int i = 0; i < length; i++) {
            rs2_vertex v = data.position(i);
            vertices[i] = new Vertex(v.xyz(0), v.xyz(1), v.xyz(2));
        }

        return vertices;
    }

    public rs2_pixel getTextureCoordinateData() {
        rs2_pixel pixelPtr = rs2_get_frame_texture_coordinates(instance, RealSenseError.getInstance());
        RealSenseError.checkError();
        return pixelPtr;
    }

    public Pixel[] getTextureCoordinates() {
        // todo: speed this up -> take a look at c#
        int length = count();
        rs2_pixel data = getTextureCoordinateData();
        Pixel[] pixels = new Pixel[length];

        for (int i = 0; i < length; i++) {
            rs2_pixel p = data.position(i);
            pixels[i] = new Pixel(p.ij(0), p.ij(1));
        }

        return pixels;
    }

    public void exportToPly(String fileName, Frame texture) {
        rs2_export_to_ply(instance, fileName, texture.instance, RealSenseError.getInstance());
        RealSenseError.checkError();
    }
}
