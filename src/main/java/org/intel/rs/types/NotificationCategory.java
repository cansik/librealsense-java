package org.intel.rs.types;

public enum NotificationCategory
{
    FramesTimeout(0),
    FrameCorrupted(1),
    HardwareError(2),
    UnknownError(3);

    private int index;

    NotificationCategory(int index) {
        this.index = index;
    }

    public static NotificationCategory fromIndex(int index) {
        for (NotificationCategory type : values()) {
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
