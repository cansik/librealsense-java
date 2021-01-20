package org.intel.rs.test;

import org.intel.rs.Context;
import org.intel.rs.frame.FrameList;
import org.intel.rs.frame.MotionFrame;
import org.intel.rs.pipeline.Config;
import org.intel.rs.pipeline.Pipeline;
import org.intel.rs.types.Format;
import org.intel.rs.types.Stream;
import org.junit.Test;

public class FrameTest {

    @Test
    public void motionStreamTest() {
        Context context = new Context();

        // realsense
        Config config = new Config();
        Pipeline pipeline = new Pipeline(context);

        config.enableStream(Stream.Accel, Format.Xyz32f);
        config.enableStream(Stream.Gyro, Format.Xyz32f);

        System.out.println("starting camera...");
        pipeline.start(config);

        System.out.println("done!");

        // read motion frames
        for(int i = 0; i < 100; i++) {
            FrameList frames = pipeline.waitForFrames();

            MotionFrame accelFrame = frames.getAccelerometerFrame();
            MotionFrame gyroFrame = frames.getGyroscopeFrame();

            float[] accelData = accelFrame.getMotionData();
            float[] gyroData = gyroFrame.getMotionData();

            accelFrame.release();
            gyroFrame.release();

            System.out.printf("Accelerometer: %.2f %.2f %.2f\t", accelData[0], accelData[1], accelData[2]);
            System.out.printf("Gyroscope: %.2f %.2f %.2f\n", gyroData[0], gyroData[1], gyroData[2]);

            frames.release();
        }

        pipeline.stop();
        pipeline.release();
        context.release();
    }
}
