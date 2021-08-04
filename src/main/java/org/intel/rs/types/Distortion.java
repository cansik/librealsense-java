package org.intel.rs.types;

public enum Distortion
{
    // Rectilinear images. No distortion compensation required.
    None(0),

    // Equivalent to Brown-Conrady distortion), except that tangential distortion is applied to radially distorted points
    ModifiedBrownConrady(1),

    // Equivalent to Brown-Conrady distortion), except undistorts image instead of distorting it
    InverseBrownConrady(2),

    // F-Theta fish-eye distortion model
    Ftheta(3),

    // Unmodified Brown-Conrady distortion model
    BrownConrady(4),

    // Four parameter Kannala Brandt distortion model
    KannalaBrandt4(5),;

    private int index;

    Distortion(int index) {
        this.index = index;
    }

    public static Distortion fromIndex(int index) {
        for (Distortion type : values()) {
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
