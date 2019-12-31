package org.intel.rs.types;

public enum ExceptionType
{
    Unknown(0),
    CameraDisconnected(1),
    Backend(2),
    InvalidValue(3),
    WrongApiCallSequence(4),
    NotImplemented(5),
    DeviceInRecoveryMode(6),
    Io(7);

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
