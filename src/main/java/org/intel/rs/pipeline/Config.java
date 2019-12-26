package org.intel.rs.pipeline;

import static org.intel.rs.util.RealSenseUtil.checkError;
import static org.bytedeco.librealsense2.global.realsense2.*;

import org.bytedeco.librealsense2.*;
import org.intel.rs.Context;
import org.intel.rs.util.NativeDecorator;

public class Config implements NativeDecorator<rs2_config> {
    rs2_config instance;

    public Config(rs2_config instance) {
        this.instance = instance;
    }

    @Override
    public rs2_config getInstance() {
        return instance;
    }

    @Override
    public void release() {
        rs2_delete_config(instance);
    }
}