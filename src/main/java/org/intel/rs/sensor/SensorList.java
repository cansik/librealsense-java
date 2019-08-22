package org.intel.rs.sensor;

import org.intel.rs.util.NativeDecorator;

import static org.intel.rs.api.RealSense.*;
import static org.intel.rs.util.RealSenseUtil.*;

public class SensorList implements NativeDecorator<rs2_sensor_list> {
    rs2_sensor_list instance;

    public SensorList(rs2_sensor_list instance) {
        this.instance = instance;
    }

    public Sensor get(int index) {
        rs2_error error = new rs2_error();
        rs2_sensor sensor = rs2_create_sensor(instance, index, error);
        checkError(error);
        return new Sensor(sensor);
    }

    public int count() {
        rs2_error error = new rs2_error();
        int sensorCount = rs2_get_sensors_count(instance, error);
        checkError(error);

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
}
