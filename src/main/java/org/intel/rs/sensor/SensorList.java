package org.intel.rs.sensor;

import org.bytedeco.librealsense2.rs2_sensor;
import org.bytedeco.librealsense2.rs2_sensor_list;
import org.intel.rs.util.NativeDecorator;
import org.intel.rs.util.NativeList;
import org.intel.rs.util.NativeListIterator;
import org.intel.rs.util.RealSenseError;

import java.util.Iterator;

import static org.bytedeco.librealsense2.global.realsense2.*;

public class SensorList implements NativeDecorator<rs2_sensor_list>, NativeList<Sensor> {
    rs2_sensor_list instance;

    public SensorList(rs2_sensor_list instance) {
        this.instance = instance;
    }

    @Override
    public Sensor get(int index) {
        rs2_sensor sensor = rs2_create_sensor(instance, index, RealSenseError.getInstance());
        RealSenseError.checkError();
        return new Sensor(sensor);
    }

    @Override
    public int count() {
        int sensorCount = rs2_get_sensors_count(instance, RealSenseError.getInstance());
        RealSenseError.checkError();

        return sensorCount;
    }

    @Override
    public rs2_sensor_list getInstance() {
        return instance;
    }

    @Override
    public void release() {
        rs2_delete_sensor_list(instance);
    }

    @Override
    public Iterator<Sensor> iterator() {
        return new NativeListIterator<>(this);
    }
}
