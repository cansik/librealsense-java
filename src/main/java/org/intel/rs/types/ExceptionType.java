package org.intel.rs.types;

public enum ExceptionType
{
    Unknown(0),

    // Device was disconnected), this can be caused by outside intervention), by internal firmware error or due to insufficient power
    CameraDisconnected(1),

    // Error was returned from the underlying OS-specific layer
    Backend(2),

    // Invalid value was passed to the API
    InvalidValue(3),

    // Function precondition was violated
    WrongApiCallSequence(4),

    // The method is not implemented at this point
    NotImplemented(5),

    // Device is in recovery mode and might require firmware update
    DeviceInRecoveryMode(6),

    // IO Device failure
    Io(7),;

    private int index;

    ExceptionType(int index) {
        this.index = index;
    }

    public static ExceptionType fromIndex(int index) {
        for (ExceptionType type : values()) {
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
