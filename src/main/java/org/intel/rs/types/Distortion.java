package org.intel.rs.types;

public enum Distortion
{
    None(0),
    ModifiedBrownConrady(1),
    InverseBrownConrady(2),
    Ftheta(3),
    BrownConrady(4);

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
