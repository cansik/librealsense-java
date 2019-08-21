package org.inte.rs.test;

import org.intel.rs.Context;
import org.intel.rs.DeviceList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DeviceTest {

    private Context context;

    @Before
    public void createContext() {
        context = new Context();
    }

    @After
    public void closeContext() {
        context.close();
    }

    @Test
    public void deviceCountTest() {
        DeviceList list = context.queryDevices();
        int count = list.getCount();
        System.out.println("Device Count: " + count);
    }

}
