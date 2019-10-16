package org.intel.rs.util;

import static org.bytedeco.librealsense2.global.realsense2.*;

import org.bytedeco.librealsense2.*;

public class TimeStampDomain {
    int instance;

    public TimeStampDomain(int instance) {
        this.instance = instance;
    }

    public boolean is(int tsDomain) {
        return instance == tsDomain;
    }

    public boolean isHardwareClock() {
        return is(RS2_TIMESTAMP_DOMAIN_HARDWARE_CLOCK);
    }

    public boolean isSystemTime() {
        return is(RS2_TIMESTAMP_DOMAIN_SYSTEM_TIME);
    }

    public boolean isDomainCount() {
        return is(RS2_TIMESTAMP_DOMAIN_COUNT);
    }

    public int getValue() {
        return instance;
    }
}