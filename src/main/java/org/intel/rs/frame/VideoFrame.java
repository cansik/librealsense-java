package org.intel.rs.frame;

import static org.bytedeco.librealsense2.global.realsense2.*;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.librealsense2.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.intel.rs.util.RealSenseError;

public class VideoFrame extends Frame {
    public VideoFrame(rs2_frame instance) {
        super(instance);
    }

    public int getWidth() {
        int width = rs2_get_frame_width(instance, RealSenseError.getInstance());
        RealSenseError.checkError();
        return width;
    }

    public int getHeight() {
        int height = rs2_get_frame_height(instance, RealSenseError.getInstance());
        RealSenseError.checkError();
        return height;
    }

    public int getStride() {
        int stride = rs2_get_frame_stride_in_bytes(instance, RealSenseError.getInstance());
        RealSenseError.checkError();
        return stride;
    }

    public int getBitsPerPixel() {
        int bpp = rs2_get_frame_bits_per_pixel(instance, RealSenseError.getInstance());
        RealSenseError.checkError();
        return bpp;
    }


    public ByteBuffer getManagedBuffer() {
        // todo: make faster by reusing managed buffer
        ByteBuffer directBuffer = getData();
        ByteBuffer managedBuffer = ByteBuffer.allocate(directBuffer.capacity());
        managedBuffer.put(directBuffer);

        return managedBuffer;
    }

    /**
     * Copies the pixels (3-Component) into a managed array.
     *
     * @return Managed int array of pixels.
     */
    public void copyTo(int[] pixels) {
        Pointer dataPtr = getDataPointer();
        int size = getDataSize();

        BytePointer ptr = new BytePointer(dataPtr);
        ptr.capacity(size);

        ByteBuffer rawPixels = ptr.asBuffer();

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = ((rawPixels.get() & 0xFF) << 16)
                    | ((rawPixels.get() & 0xFF) << 8)
                    | ((rawPixels.get() & 0xFF));
        }
    }
}
