package org.intel.rs.types;

public enum CameraInfo
{
    // Friendly name
    Name(0),

    // Device serial number
    SerialNumber(1),

    // Primary firmware version
    FirmwareVersion(2),

    // Recommended firmware version
    RecommendedFirmwareVersion(3),

    // Unique identifier of the port the device is connected to (platform specific)
    PhysicalPort(4),

    // If device supports firmware logging), this is the command to send to get logs from firmware
    DebugOpCode(5),

    // True iff the device is in advanced mode
    AdvancedMode(6),

    // Product ID as reported in the USB descriptor
    ProductId(7),

    // True iff EEPROM is locked
    CameraLocked(8),

    // Designated USB specification: USB2/USB3
    UsbTypeDescriptor(9);

    private int index;

    CameraInfo(int index) {
        this.index = index;
    }

    public static CameraInfo fromIndex(int index) {
        for (CameraInfo type : values()) {
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
