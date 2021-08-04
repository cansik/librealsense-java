package org.intel.rs.types;

public enum LogSeverity
{
    // Detailed information about ordinary operations
    Debug(0),

    // Terse information about ordinary operations
    Info(1),

    // Indication of possible failure
    Warn(2),

    // Indication of definite failure
    Error(3),

    // Indication of unrecoverable failure
    Fatal(4),

    // No logging will occur
    None(5);

    private int index;

    LogSeverity(int index) {
        this.index = index;
    }

    public static LogSeverity fromIndex(int index) {
        for (LogSeverity type : values()) {
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
