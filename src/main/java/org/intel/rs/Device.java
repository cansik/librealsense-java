package org.intel.rs;

import static org.intel.rs.api.RealSense.*;
import static org.intel.rs.api.RealSenseUtil.*;

public class Device {
    protected rs2_device instance;

    public Device(rs2_device instance) {
        this.instance = instance;
    }
}
