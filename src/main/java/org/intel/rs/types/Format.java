package org.intel.rs.types;

public enum Format {
    // When passed to enable stream), librealsense will try to provide best suited format
    Any(0),

    // 16-bit linear depth values. The depth is meters is equal to depth scale * pixel value.
    Z16(1),

    // 16-bit float-point disparity values. Depth->Disparity conversion : Disparity(Baseline*FocalLength/Depth.
    Disparity16(2),

    // 32-bit floating point 3D coordinates.
    Xyz32f(3),

    // 32-bit y0), u), y1), v data for every two pixels. Similar to YUV422 but packed in a different order - https://en.wikipedia.org/wiki/YUV
    Yuyv(4),

    // 8-bit red), green and blue channels
    Rgb8(5),

    // 8-bit blue), green), and red channels -- suitable for OpenCV
    Bgr8(6),

    // 8-bit red), green and blue channels + constant alpha channel equal to FF
    Rgba8(7),

    // 8-bit blue), green), and red channels + constant alpha channel equal to FF
    Bgra8(8),

    // 8-bit per-pixel grayscale image
    Y8(9),

    // 16-bit per-pixel grayscale image
    Y16(10),

    // Four 10-bit luminance values encoded into a 5-byte macropixel
    Raw10(11),

    // 16-bit raw image
    Raw16(12),

    // 8-bit raw image
    Raw8(13),

    // Similar to the standard YUYV pixel format), but packed in a different order
    Uyvy(14),

    // Raw data from the motion sensor
    MotionRaw(15),

    // Motion data packed as 3 32-bit float values), for X), Y), and Z axis
    MotionXyz32f(16),

    // Raw data from the external sensors hooked to one of the GPIO's
    GpioRaw(17),

    // Pose data packed as floats array), containing translation vector), rotation quaternion and prediction velocities and accelerations vectors
    SixDOF(18),

    // 32-bit float-point disparity values. Depth->Disparity conversion : Disparity(Baseline*FocalLength/Depth
    Disparity32(19),

    // 16-bit per-pixel grayscale image unpacked from 10 bits per pixel packed ([8:8:8:8:2222]) grey-scale image. The data is unpacked to LSB and padded with 6 zero bits
    Y10BPack(20),

    // 32-bit float-point depth distance value.
    Distance(21),

    // Bitstream encoding for video in which an image of each frame is encoded as JPEG-DIB.
    Mjpeg(22),

    // 8-bit per pixel interleaved. 8-bit left), 8-bit right.
    Y8i(23),

    // 12-bit per pixel interleaved. 12-bit left), 12-bit right. Each pixel is stored in a 24-bit word in little-endian order.
    Y12I(24),

    // multi-planar Depth 16bit + IR 10bit.
    Inzi(25),

    // 8-bit IR stream.
    Invi(26),

    // Grey-scale image as a bit-packed array. 4 pixel data stream taking 5 bytes.
    W10(27),

    // Variable-length Huffman-compressed 16-bit depth values.
    Z16H(28),

    // 16-bit per-pixel frame grabber format.
    FG(29);

    private int index;

    Format(int index) {
        this.index = index;
    }

    public static Format fromIndex(int index) {
        for (Format type : values()) {
            if (type.getIndex() == index) {
                return type;
            }
        }
        return null;
    }

    public int getIndex() {
        return index;
    }
}
