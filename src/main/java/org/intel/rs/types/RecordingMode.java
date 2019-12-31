package org.intel.rs.types;

public enum RecordingMode
{
    BlankFrames(0),
    Compressed(1),
    BestQuality(2);

    private int index;

    RecordingMode(int index) {
        this.index = index;
    }

    public static RecordingMode fromIndex(int index) {
        for (RecordingMode type : values()) {
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
