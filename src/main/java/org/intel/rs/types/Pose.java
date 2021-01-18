package org.intel.rs.types;

import org.bytedeco.librealsense2.rs2_pose;
import org.bytedeco.librealsense2.rs2_quaternion;
import org.bytedeco.librealsense2.rs2_vector;
import org.intel.rs.types.math.Quaternion;
import org.intel.rs.types.math.Vector;

public class Pose {
    private final Vector translation;
    private final Vector velocity;
    private final Vector acceleration;
    private final Quaternion rotation;
    private final Vector angular_velocity;
    private final Vector angularAcceleration;
    private final int trackerConfidence;
    private final int mapperConfidence;

    public Pose(rs2_pose pose) {
        rs2_vector raw = pose.translation();
        translation = new Vector(raw.x(), raw.y(), raw.z());

        raw = pose.velocity();
        velocity = new Vector(raw.x(), raw.y(), raw.z());

        raw = pose.acceleration();
        acceleration = new Vector(raw.x(), raw.y(), raw.z());

        rs2_quaternion quat = pose.rotation();
        rotation = new Quaternion(quat.x(), quat.y(), quat.z(), quat.w());

        raw = pose.angular_velocity();
        angular_velocity = new Vector(raw.x(), raw.y(), raw.z());

        raw = pose.angular_acceleration();
        angularAcceleration = new Vector(raw.x(), raw.y(), raw.z());

        trackerConfidence = pose.tracker_confidence();
        mapperConfidence = pose.mapper_confidence();
    }

    /**
     * X, Y, Z values of translation, in meters (relative to initial position)
     *
     * @return Vector.
     */
    public Vector getTranslation() {
        return translation;
    }

    /**
     * X, Y, Z values of velocity, in meter/sec
     *
     * @return Vector.
     */
    public Vector getVelocity() {
        return velocity;
    }

    /**
     * X, Y, Z values of acceleration, in meter/sec^2
     *
     * @return Vector.
     */
    public Vector getAcceleration() {
        return acceleration;
    }

    /**
     * Qi, Qj, Qk, Qr components of rotation as represented in quaternion rotation (relative to initial position)
     *
     * @return Quaternion.
     */
    public Quaternion getRotation() {
        return rotation;
    }

    /**
     * X, Y, Z values of angular velocity, in radians/sec
     *
     * @return Vector.
     */
    public Vector getAngular_velocity() {
        return angular_velocity;
    }

    /**
     * X, Y, Z values of angular acceleration, in radians/sec^2
     *
     * @return Vector.
     */
    public Vector getAngularAcceleration() {
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
