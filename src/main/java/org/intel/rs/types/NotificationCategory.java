package org.intel.rs.types;

public enum NotificationCategory
{
    // Frames didn't arrived within 5 seconds
    FramesTimeout(0),

    // Received partial/incomplete frame
    FrameCorrupted(1),

    // Error reported from the device
    HardwareError(2),

    // General Hardeware notification that is not an error
    HardwareEvent(3),

    // Received unknown error from the device
    UnknownError(4),

    // Current firmware version installed is not the latest available
    FirmwareUpdateRecommended(5),

    // A relocalization event has updated the pose provided by a pose sensor
    PoseRelocalization(6);

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
