package org.intel.rs.types;

public enum Format {
    Any(0),
    Z16(1),
    Disparity16(2),
    Xyz32f(3),
    Yuyv(4),
    Rgb8(5),
    Bgr8(6),
    Rgba8(7),
    Bgra8(8),
    Y8(9),
    Y16(10),
    Raw10(11),
    Raw16(12),
    Raw8(13),
    Uyvy(14),
    MotionRaw(15),
    MotionXyz32f(16),
    GpioRaw(17);

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
