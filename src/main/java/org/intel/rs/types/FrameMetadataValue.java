package org.intel.rs.types;

public enum FrameMetadataValue
{
    FrameCounter(0),
    FrameTimestamp(1),
    SensorTimestamp(2),
    ActualExposure(3),
    GainLevel(4),
    AutoExposure(5),
    WhiteBalance(6),
    TimeOfArrival(7),
    Temperature(8),
    BackendTimestamp(9),
    ActualFps(10),
    FrameLaserPower(11),
    FrameLaserPowerMode(12),
    ExposurePriority(13),
    ExposureRoiLeft(14),
    ExposureRoiRight(15),
    ExposureRoiTop(16),
    ExposureRoiBottom(17),
    Brightness(18),
    Contrast(19),
    Saturation(20),
    Sharpness(21),
    AutoWhiteBalanceTemperature(22),
    BacklightCompensation(23),
    Hue(24),
    Gamma(25),
    ManualWhiteBalance(26),
    PowerLineFrequency(27),
    LowLightCompensation(28);

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
