package org.intel.rs.types;

public enum TimestampDomain
{
    //  Frame timestamp was measured in relation to the camera clock
    HardwareClock(0),

    //  Frame timestamp was measured in relation to the OS system clock
    SystemTime(1),

    GlobalTime(2);

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
