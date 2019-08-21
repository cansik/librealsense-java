package org.inte.rs.test;

import org.intel.rs.Context;
import org.intel.rs.DeviceList;
import org.intel.rs.api.RealSenseUtil;
import org.junit.Test;

public class ContextTest {

    @Test
    public void createContextTest() {
        Context context = new Context();
        assert !RealSenseUtil.errorFlag;
        context.close();
    }

    @Test
    public void closeContextTest() {
        Context context = new Context();
        context.close();
        assert !RealSenseUtil.errorFlag;
    }

    @Test
    public void queryDevicesTest() {
        Context context = new Context();
        DeviceList devices = context.queryDevices();
        assert !RealSenseUtil.errorFlag;
        context.close();
    }
}
