package org.intel.rs.types;

import org.bytedeco.librealsense2.rs2_pose;
import org.bytedeco.librealsense2.rs2_quaternion;
import org.bytedeco.librealsense2.rs2_vector;

public class Pose {
    private final float[] translation;
    private final float[] velocity;
    private final float[] acceleration;
    private final float[] rotation;
    private final float[] angular_velocity;
    private final float[] angularAcceleration;
    private final int trackerConfidence;
    private final int mapperConfidence;

    public Pose(rs2_pose pose) {
        rs2_vector raw = pose.translation();
        translation = new float[]{raw.x(), raw.y(), raw.z()};

        raw = pose.velocity();
        velocity = new float[]{raw.x(), raw.y(), raw.z()};

        raw = pose.acceleration();
        acceleration = new float[]{raw.x(), raw.y(), raw.z()};

        rs2_quaternion quat = pose.rotation();
        rotation = new float[]{quat.x(), quat.y(), quat.z(), quat.w()};

        raw = pose.angular_velocity();
        angular_velocity = new float[]{raw.x(), raw.y(), raw.z()};

        raw = pose.angular_acceleration();
        angularAcceleration = new float[]{raw.x(), raw.y(), raw.z()};

        trackerConfidence = pose.tracker_confidence();
        mapperConfidence = pose.mapper_confidence();
    }

    /**
     * X, Y, Z values of translation, in meters (relative to initial position)
     *
     * @return Vector.
     */
    public float[] getTranslation() {
        return translation;
    }

    /**
     * X, Y, Z values of velocity, in meter/sec
     *
     * @return Vector.
     */
    public float[] getVelocity() {
        return velocity;
    }

    /**
     * X, Y, Z values of acceleration, in meter/sec^2
     *
     * @return Vector.
     */
    public float[] getAcceleration() {
        return acceleration;
    }

    /**
     * Qi, Qj, Qk, Qr components of rotation as represented in quaternion rotation (relative to initial position)
     *
     * @return Quaternion.
     */
    public float[] getRotation() {
        return rotation;
    }

    /**
     * X, Y, Z values of angular velocity, in radians/sec
     *
     * @return Vector.
     */
    public float[] getAngular_velocity() {
        return angular_velocity;
    }

    /**
     * X, Y, Z values of angular acceleration, in radians/sec^2
     *
     * @return Vector.
     */
    public float[] getAngularAcceleration() {
        return angularAcceleration;
    }

    /**
     * pose data confidence 0x0 - Failed, 0x1 - Low, 0x2 - Medium, 0x3 - High
     *
     * @return Int.
     */
    public int getTrackerConfidence() {
        return trackerConfidence;
    }

    /**
     * pose data confidence 0x0 - Failed, 0x1 - Low, 0x2 - Medium, 0x3 - High
     *
     * @return Int.
     */
    public int getMapperConfidence() {
        return mapperConfidence;
    }
}
