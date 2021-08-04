package org.intel.rs.types;

public enum Stream {
    Any(0),

    // Native stream of depth data produced by RealSense device
    Depth(1),

    // Native stream of color data captured by RealSense device
    Color(2),

    // Native stream of infrared data captured by RealSense device
    Infrared(3),

    // Native stream of fish-eye (wide) data captured from the dedicate motion camera
    Fisheye(4),

    // Native stream of gyroscope motion data produced by RealSense device
    Gyro(5),

    // Native stream of accelerometer motion data produced by RealSense device
    Accel(6),

    // Signals from external device connected through GPIO
    Gpio(7),

    // 6 Degrees of Freedom pose data, calculated by RealSense device
    Pose(8),
    Confidence(9);

    private int index;

    Stream(int index) {
        this.index = index;
    }

    public static Stream fromIndex(int index) {
        for (Stream type : values()) {
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
