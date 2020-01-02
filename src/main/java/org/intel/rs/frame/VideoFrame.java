package org.intel.rs.frame;

import static org.bytedeco.librealsense2.global.realsense2.*;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.librealsense2.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.intel.rs.util.RealSenseUtil.checkError;

public class VideoFrame extends Frame {
    public VideoFrame(rs2_frame instance) {
        super(instance);
    }

    public int getWidth() {
        rs2_error error = new rs2_error();
        int width = rs2_get_frame_width(instance, error);
        checkError(error);
        return width;
    }

    public int getHeight() {
        rs2_error error = new rs2_error();
        int height = rs2_get_frame_height(instance, error);
        checkError(error);
        return height;
    }

    public int getStride() {
        rs2_error error = new rs2_error();
        int stride = rs2_get_frame_stride_in_bytes(instance, error);
        checkError(error);
        return stride;
    }

    public int getBitsPerPixel() {
        rs2_error error = new rs2_error();
        int bpp = rs2_get_frame_bits_per_pixel(instance, error);
        checkError(error);
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
