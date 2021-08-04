package org.intel.rs.types;

public enum PlaybackStatus
{
    //Unknown state
    Unknown(0),

    //One or more sensors were started), playback is reading and raising data
    Playing(1),

    //One or more sensors were started), but playback paused reading and paused raising dat
    Paused(2),

    //All sensors were stopped), or playback has ended (all data was read). This is the initial playback status
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
