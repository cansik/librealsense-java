package org.intel.rs.types;

public enum PlaybackStatus
{
    Unknown(0),
    Playing(1),
    Paused(2),
    Stopped(3);

    private int index;

    PlaybackStatus(int index) {
        this.index = index;
    }

    public static PlaybackStatus fromIndex(int index) {
        for (PlaybackStatus type : values()) {
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
