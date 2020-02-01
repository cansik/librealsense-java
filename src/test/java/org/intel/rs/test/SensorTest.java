package org.intel.rs.test;

import org.intel.rs.*;
import org.intel.rs.device.Device;
import org.intel.rs.device.DeviceList;
import org.intel.rs.sensor.SensorList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SensorTest {

    private Context context;
    private Device device;

    @Before
    public void create() {
        context = new Context();
        DeviceList list = context.queryDevices();
        int count = list.count();
        if(count < 1) return;

        device = list.get(0);
        list.release();
    }

    @After
    public void close() {
        if(device != null)
            device.release();
        context.release();
    }

    @Test
    public void querySensors() {
        if(device == null)
            return;

        SensorList sensors = device.querySensors();
        int count = sensors.count();
        System.out.println("SensorCount: " + count);
        sensors.count();
    }
}
