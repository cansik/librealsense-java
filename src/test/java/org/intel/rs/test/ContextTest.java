package org.intel.rs.test;

import org.intel.rs.Context;
import org.intel.rs.device.Device;
import org.intel.rs.device.DeviceList;
import org.intel.rs.util.RealSenseException;
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

    @Test
    public void fireExceptionTest(){
        Context context = new Context();
        DeviceList devices = context.queryDevices();

        try {
            Device device = devices.get(100);
        } catch (RealSenseException ex) {
            System.out.println(ex);
        }
        context.release();
    }
}
