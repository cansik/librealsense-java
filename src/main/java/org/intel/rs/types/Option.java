package org.intel.rs.types;

public enum Option {
    // Enable / disable color backlight compensation
    BacklightCompensation(0),

    // Color image brightness
    Brightness(1),

    // Color image contrast
    Contrast(2),

    // Controls exposure time of color camera. Setting any value will disable auto exposure
    Exposure(3),

    // Color image gain
    Gain(4),

    // Color image gamma setting
    Gamma(5),

    // Color image hue
    Hue(6),

    // Color image saturation setting
    Saturation(7),

    // Color image sharpness setting
    Sharpness(8),

    // Controls white balance of color image. Setting any value will disable auto white balance
    WhiteBalance(9),

    // Enable / disable color image auto-exposure
    EnableAutoExposure(10),

    // Enable / disable color image auto-white-balance
    EnableAutoWhiteBalance(11),

    // Provide access to several recommend sets of option presets for the depth camera
    VisualPreset(12),

    // Power of the F200 / SR300 projector), with 0 meaning projector off
    LaserPower(13),

    // Set the number of patterns projected per frame. The higher the accuracy value the more patterns projected. Increasing the number of patterns help to achieve better accuracy. Note that this control is affecting the Depth FPS
    Accuracy(14),

    // Motion vs. Range trade-off), with lower values allowing for better motion sensitivity and higher values allowing for better depth range
    MotionRange(15),

    // Set the filter to apply to each depth frame. Each one of the filter is optimized per the application requirements
    FilterOption(16),

    // The confidence level threshold used by the Depth algorithm pipe to set whether a pixel will get a valid range or will be marked with invalid range
    ConfidenceThreshold(17),

    // Laser Emitter enabled
    EmitterEnabled(18),

    // Number of frames the user is allowed to keep per stream. Trying to hold-on to more frames will cause frame-drops.
    FramesQueueSize(19),

    // Total number of detected frame drops from all streams
    TotalFrameDrops(20),

    // Auto-Exposure modes: Static), Anti-Flicker and Hybrid
    AutoExposureMode(21),

    // Power Line Frequency control for anti-flickering Off/50Hz/60Hz/Auto
    PowerLineFrequency(22),

    // Current Asic Temperature
    AsicTemperature(23),

    // disable error handling
    ErrorPollingEnabled(24),

    // Current Projector Temperature
    ProjectorTemperature(25),

    // Enable / disable trigger to be outputed from the camera to any external device on every depth frame
    OutputTriggerEnabled(26),

    // Current Motion-Module Temperature
    MotionModuleTemperature(27),

    // Number of meters represented by a single depth unit
    DepthUnits(28),

    // Enable/Disable automatic correction of the motion data
    EnableMotionCorrection(29),

    // Allows sensor to dynamically ajust the frame rate depending on lighting conditions
    AutoExposurePriority(30),

    // Color scheme for data visualization
    ColorScheme(31),

    // Perform histogram equalization post-processing on the depth data
    HistogramEqualizationEnabled(32),

    // Minimal distance to the target
    MinDistance(33),

    // Maximum distance to the target
    MaxDistance(34),

    // Texture mapping stream unique ID
    TextureSource(35),

    // The 2D-filter effect. The specific interpretation is given within the context of the filter
    FilterMagnitude(36),

    // 2D-filter parameter controls the weight/radius for smoothing.
    FilterSmoothAlpha(37),

    // 2D-filter range/validity threshold
    FilterSmoothDelta(38),

    // Enhance depth data post-processing with holes filling where appropriate
    HolesFill(39),

    // The distance in mm between the first and the second imagers in stereo-based depth cameras
    StereoBaseline(40),

    // Allows dynamically ajust the converge step value of the target exposure in Auto-Exposure algorithm
    AutoExposureConvergeStep(41),

    // Impose Inter-camera HW synchronization mode. Applicable for D400/Rolling Shutter SKUs
    InterCamSyncMode(42),

    // Select a stream to process
    StreamFilter(43),

    // Select a stream format to process
    StreamFormatFilter(44),

    // Select a stream index to process
    StreamIndexFilter(45),

    // When supported), this option make the camera to switch the emitter state every frame. 0 for disabled), 1 for enabled
    EmitterOnOff(46),

    // Deprecated!!! - Zero order point x
    ZeroOrderPointX(47),

    // Deprecated!!! - Zero order point y
    ZeroOrderPointY(48),

    // LLD temperature
    LLDTemperature(49),

    // MC temperature
    MCTemperature(50),

    // MA temperature
    MATemperature(51),

    // Hardware stream configuration
    HardwarePreset(52),

    // Disable global time
    GlobalTimeEnabled(53),

    // APD temperature
    APDTemperature(54),

    // Enable an internal map
    EnableMapping(55),

    // Enable appearance based relocalization
    EnableRelocalization(56),

    // Enable position jumping
    EnablePoseJumping(57),

    // Enable dynamic calibration
    EnableDynamicCalibration(58),

    // Offset from sensor to depth origin in millimetrers
    DepthOffset(59),

    // Power of the LED (light emitting diode)), with 0 meaning LED off
    LedPower(60),

    // Deprecated!!! - Toggle Zero-Order mode
    ZeroOrderEnabled(61),

    // Preserve previous map when starting
    EnableMapPreservation(62),

    // Enable/disable sensor shutdown when a free-fall is detected (on by default)
    FreeFallDetectionEnabled(63),

    // Changes the exposure time of Avalanche Photo Diode in the receiver
    APDExposureTime(64),

    // Changes the amount of sharpening in the post-processed image
    PostProcessingSharpening(65),

    // Changes the amount of sharpening in the pre-processed image
    PreProcessingSharpening(66),

    // Control edges and background noise
    NoiseFilterLevel(67),

    // Enabledisable pixel invalidation
    InvalidationBypass(68),

    // Deprecated - Use digital gain option), Change the depth ambient light see rs2_ambient_light for values
    AmbientLightEnvLevel(69),

    // Change the depth digital gain see rs2_digital_gain for values
    DigitalGain(69),

    // The resolution mode: see rs2_sensor_mode for values
    SensorMode(70),

    // Enable Laser On constantly (GS SKU Only)
    EmitterAlwaysOn(71),

    // Depth Thermal Compensation for selected D400 SKUs
    ThermalCompensation(72),

    // Camera Accuracy Health
    TriggerCameraAccuracyHealth(73),

    // Reset Camera Accuracy Health
    ResetCameraAccuracyHealth(74),

    // Host Performance
    HostPerformance(75),

    // HDR Enabled (ON(1), OFF(0) - for D400 SKUs
    HdrEnabled(76),

    // Subpreset sequence Name - for D400 SKUs
    SequenceName(77),

    // Subpreset sequence size - for D400 SKUs
    SequenceSize(78),

    // Subpreset sequence id - for D400 SKUs
    SequenceId(79),

    // Humidity temperature [Deg Celsius]
    HumidityTemperature(80),

    // Turn on/off the maximum usable range who calculates the maximum range of the camera given the amount of ambient light in the scene
    EnableMaxUsableRange(81),

    // Turn on/off the alternate IR), When enabling alternate IR), the IR image is holding the amplitude of the depth correlation.
    AlternateIR(82),

    // Noise estimation on the IR image
    NoiseEstimation(83),

    // Enables data collection for calculating IR pixel reflectivity
    EnableIrReflectivity(84),

    // Auto exposure limit - for D400 SKUs
    auto_exposure_limit(85),

    // auto gain limit - for D400 SKUs
    auto_gain_limit(86);

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
