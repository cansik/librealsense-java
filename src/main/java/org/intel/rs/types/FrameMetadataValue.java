package org.intel.rs.types;

public enum FrameMetadataValue
{
    //A sequential index managed per-stream. Integer value
    FrameCounter(0),

    //Timestamp set by device clock when data readout and transmit commence. usec
    FrameTimestamp(1),

    //Timestamp of the middle of sensor's exposure calculated by device. usec
    SensorTimestamp(2),

    //Sensor's exposure width. When Auto Exposure (AE) is on the value is controlled by firmware. usec
    ActualExposure(3),

    //A relative value increasing which will increase the Sensor's gain factor.  When AE is set On), the value is controlled by firmware. Integer value
    GainLevel(4),

    //Auto Exposure Mode indicator. Zero corresponds to AE switched off.
    AutoExposure(5),

    //White Balance setting as a color temperature. Kelvin degrees
    WhiteBalance(6),

    //Time of arrival in system clock
    TimeOfArrival(7),

    //Temperature of the device), measured at the time of the frame capture. Celsius degrees
    Temperature(8),

    //Timestamp get from uvc driver. usec
    BackendTimestamp(9),

    //Actual fps
    ActualFps(10),

    //Laser power value 0-360.
    FrameLaserPower(11),

    //Laser power mode. Zero corresponds to Laser power switched off and one for switched on.
    FrameLaserPowerMode(12),

    //Exposure priority.
    ExposurePriority(13),

    //Left region of interest for the auto exposure Algorithm.
    ExposureRoiLeft(14),

    //Right region of interest for the auto exposure Algorithm.
    ExposureRoiRight(15),

    //Top region of interest for the auto exposure Algorithm.
    ExposureRoiTop(16),

    //Bottom region of interest for the auto exposure Algorithm.
    ExposureRoiBottom(17),

    //Color image brightness.
    Brightness(18),

    //Color image contrast.
    Contrast(19),

    //Color image saturation.
    Saturation(20),

    //Color image sharpness.
    Sharpness(21),

    //Auto white balance temperature Mode indicator. Zero corresponds to automatic mode switched off.
    AutoWhiteBalanceTemperature(22),

    //Color backlight compensation. Zero corresponds to switched off.
    BacklightCompensation(23),

    //Color image hue.
    Hue(24),

    //Color image gamma.
    Gamma(25),

    //Color image white balance.
    ManualWhiteBalance(26),

    //Power Line Frequency for anti-flickering Off/50Hz/60Hz/Auto.
    PowerLineFrequency(27),

    //Color lowlight compensation. Zero corresponds to switched off.
    LowLightCompensation(28),

    //Emitter mode: 0 – all emitters disabled. 1 – laser enabled. 2 – auto laser enabled (opt). 3 – LED enabled (opt).
    FrameEmitterMode(29),

    //Led power value 0-360.
    FrameLedPower(30),

    //The number of transmitted payload bytes), not including metadata.
    RawFrameSizeBytes(31);

    private int index;

    FrameMetadataValue(int index) {
        this.index = index;
    }

    public static FrameMetadataValue fromIndex(int index) {
        for (FrameMetadataValue type : values()) {
            if (type.getIndex() == index) {
                return type;
            }
        }
        return null;
    }

    public int getIndex() {
        return index;
    }
}
