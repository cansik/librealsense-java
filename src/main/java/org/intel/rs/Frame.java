package org.intel.rs;

import static org.intel.rs.api.RealSense.*;
import static org.intel.rs.api.RealSenseUtil.*;

public class Frame implements Releasable  {
    rs2_frame instance;

    public Frame() {
        instance = new rs2_frame();
    }

    public Frame(rs2_frame instance) {
        this.instance = instance;
    }

    @Override
    public void release() {
        rs2_release_frame(instance);
    }
}
