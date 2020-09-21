package org.intel.rs.test;

import org.intel.rs.Context;
import org.intel.rs.device.Device;
import org.intel.rs.device.DeviceList;
import org.intel.rs.option.CameraOption;
import org.intel.rs.sensor.Sensor;
import org.intel.rs.sensor.SensorList;
import org.intel.rs.types.Option;
import org.intel.rs.types.Stream;
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

    @Test
    public void listSettings() {
        if(device == null)
            return;

        SensorList sensors = device.querySensors();

        for(Sensor sensor : sensors) {
            System.out.println("Sensor " + sensor.getName());

            for(CameraOption option : sensor.getSensorOptions()) {
                System.out.println("\t " + option + " Value: " + option.getValue() + " (" + option.getDescription() + ")");
            }
        }
    }

    @Test
    public void setAutoExposureOption() {
        if(device == null)
            return;

        SensorList sensors = device.querySensors();

        for(Sensor sensor : sensors) {
            // get color sensor as shown in the official example
            // (https://github.com/robotology/yarp/pull/2010/commits/74734a46ed92bac71d921922c0ace7d0543e69ad#diff-b2d325b49d86019f0568d92345413a79R72)
            if(sensor.getStreamProfiles().get(0).getStream() == Stream.Color) {
                System.out.println("setting auto exposure for " + sensor.getName() + "...");
                CameraOption option = sensor.getSensorOptions().get(Option.EnableAutoExposure);

                if(!option.isSupported()) {
                    System.out.println("option not supported!");
                    continue;
                }

                option.setValue(0.0f);
                System.out.println("done!");
            }
        }
    }
}
