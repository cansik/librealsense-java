package org.intel.rs;

import static org.intel.rs.api.RealSense.*;
import static org.intel.rs.api.RealSenseUtil.*;

public class Device implements AutoCloseable {
    protected rs2_device instance;

    public Device(rs2_device instance) {
        this.instance = instance;
    }

    @Override
    public void close() {
        rs2_delete_device(instance);
    }
}
