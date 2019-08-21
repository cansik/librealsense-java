package org.intel.rs;

import static org.intel.rs.api.RealSense.rs2_timestamp_domain;

public class TimeStampDomain {
    rs2_timestamp_domain instance;

    public TimeStampDomain(rs2_timestamp_domain instance)
    {
        this.instance = instance;
    }

    public boolean is(rs2_timestamp_domain tsDomain) {
        return instance.value == tsDomain.value;
    }

    public boolean isHardwareClock() {
        return is(rs2_timestamp_domain.RS2_TIMESTAMP_DOMAIN_HARDWARE_CLOCK);
    }

    public boolean isSystemTime() {
        return is(rs2_timestamp_domain.RS2_TIMESTAMP_DOMAIN_SYSTEM_TIME);
    }

    public boolean isDomainCount() {
        return is(rs2_timestamp_domain.RS2_TIMESTAMP_DOMAIN_COUNT);
    }

    public rs2_timestamp_domain getValue() {
        return instance;
    }
}