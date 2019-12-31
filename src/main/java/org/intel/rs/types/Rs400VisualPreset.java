package org.intel.rs.types;

public enum Rs400VisualPreset
{
    Custom(0),
    ShortRange(1),
    Hand(2),
    HighAccuracy(3),
    HighDensity(4),
    MediumDensity(5);

    private int index;

    Rs400VisualPreset(int index) {
        this.index = index;
    }

    public static Rs400VisualPreset fromIndex(int index) {
        for (Rs400VisualPreset type : values()) {
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
