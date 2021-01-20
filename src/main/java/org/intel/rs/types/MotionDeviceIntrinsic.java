package org.intel.rs.types;

import org.bytedeco.librealsense2.rs2_motion_device_intrinsic;

public class MotionDeviceIntrinsic {

    /* mData matrix description:
     * Scale X       cross axis  cross axis  Bias X \n
     * cross axis    Scale Y     cross axis  Bias Y \n
     * cross axis    cross axis  Scale Z     Bias Z */
    private final float[][] data;
    private final float[] noiseVariances; // Variance of noise for X, Y, and Z axis
    private final float[] biasVariances;  // Variance of bias for X, Y, and Z axis


    public MotionDeviceIntrinsic() {
        data = new float[3][4];
        noiseVariances = new float[3];
        biasVariances = new float[3];
    }

    public MotionDeviceIntrinsic(rs2_motion_device_intrinsic raw) {
        this();

        for (int y = 0; y < data.length; y++) {
            for (int x = 0; x < data[y].length; x++) {
                data[y][x] = raw.data(y, x);
            }
        }

        noiseVariances[0] = raw.noise_variances(0);
        noiseVariances[1] = raw.noise_variances(1);
        noiseVariances[2] = raw.noise_variances(2);

        biasVariances[0] = raw.bias_variances(0);
        biasVariances[1] = raw.bias_variances(1);
        biasVariances[2] = raw.bias_variances(2);
    }

    public MotionDeviceIntrinsic(float[][] data, float[] noiseVariances, float[] biasVariances) {
        this.data = data;
        this.noiseVariances = noiseVariances;
        this.biasVariances = biasVariances;
    }

    public float[][] getData() {
        return data;
    }

    public float[] getNoiseVariances() {
        return noiseVariances;
    }

    public float[] getBiasVariances() {
        return biasVariances;
    }
}
