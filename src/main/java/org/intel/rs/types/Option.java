package org.intel.rs.types;

public enum Option {
    BacklightCompensation(0),
    Brightness(1),
    Contrast(2),
    Exposure(3),
    Gain(4),
    Gamma(5),
    Hue(6),
    Saturation(7),
    Sharpness(8),
    WhiteBalance(9),
    EnableAutoExposure(10),
    EnableAutoWhiteBalance(11),
    VisualPreset(12),
    LaserPower(13),
    Accuracy(14),
    MotionRange(15),
    FilterOption(16),
    ConfidenceThreshold(17),
    EmitterEnabled(18),
    FramesQueueSize(19),
    TotalFrameDrops(20),
    AutoExposureMode(21),
    PowerLineFrequency(22),
    AsicTemperature(23),
    ErrorPollingEnabled(24),
    ProjectorTemperature(25),
    OutputTriggerEnabled(26),
    MotionModuleTemperature(27),
    DepthUnits(28),
    EnableMotionCorrection(29),
    AutoExposurePriority(30),
    ColorScheme(31),
    HistogramEqualizationEnabled(32),
    MinDistance(33),
    MaxDistance(34),
    TextureSource(35),
    FilterMagnitude(36),
    FilterSmoothAlpha(37),
    FilterSmoothDelta(38),
    HolesFill(39),
    StereoBaseline(40),
    AutoExposureConvergeStep(41),
    InterCamSyncMode(42),
    StreamFilter(43),
    StreamFormatFilter(44),
    StreamIndexFilter(45),
    EmitterOnOff(46),
    ZeroOrderPointX(47),
    ZeroOrderPointY(48),
    LLDTemperature(49),
    MCTemperature(50),
    MATemperature (51),
    APDTemperature (54);

    private int index;

    Option(int index) {
        this.index = index;
    }

    public static Option fromIndex(int index) {
        for (Option type : values()) {
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
