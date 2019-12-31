package org.intel.rs.types;

public enum LogSeverity
{
    Debug(0),
    Info(1),
    Warn(2),
    Error(3),
    Fatal(4),
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
