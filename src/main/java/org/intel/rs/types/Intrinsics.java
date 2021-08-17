package org.intel.rs.types;

import org.bytedeco.librealsense2.rs2_device;
import org.bytedeco.librealsense2.rs2_intrinsics;
import org.intel.rs.util.NativeDecorator;

public class Intrinsics implements NativeDecorator<rs2_intrinsics> {
    protected rs2_intrinsics instance;

    public Intrinsics(rs2_intrinsics instance) {
        this.instance = instance;
    }

    // todo: implement intrinsics getter

    @Override
    public rs2_intrinsics getInstance() {
        return instance;
    }

    @Override
    public void release() {
        instance.releaseReference();
        instance = null;
    }
}
