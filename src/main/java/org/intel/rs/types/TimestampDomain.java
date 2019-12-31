package org.intel.rs.types;

public enum TimestampDomain
{
    HardwareClock(0),
    SystemTime(1);

    private int index;

    TimestampDomain(int index) {
        this.index = index;
    }

    public static TimestampDomain fromIndex(int index) {
        for (TimestampDomain type : values()) {
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
