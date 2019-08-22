package org.inte.rs.test;

import org.intel.rs.Context;
import org.intel.rs.device.DeviceList;
import org.junit.Test;

public class ContextTest {

    @Test
    public void createContextTest() {
        Context context = new Context();
        context.release();
    }

    @Test
    public void closeContextTest() {
        Context context = new Context();
        context.release();
    }

    @Test
    public void queryDevicesTest() {
        Context context = new Context();
        DeviceList devices = context.queryDevices();
        context.release();
    }
}
