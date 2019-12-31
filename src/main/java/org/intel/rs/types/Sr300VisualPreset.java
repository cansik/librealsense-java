package org.intel.rs.types;

public enum Sr300VisualPreset
{
    ShortRange(0),
    LongRange(1),
    BackgroundSegmentation(2),
    GestureRecognition(3),
    ObjectScanning(4),
    FaceAnalytics(5),
    FaceLogin(6),
    GrCursor(7),
    Default(8),
    MidRange(9),
    IrOnly(10);

    private int index;

    Sr300VisualPreset(int index) {
        this.index = index;
    }

    public static Sr300VisualPreset fromIndex(int index) {
        for (Sr300VisualPreset type : values()) {
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
