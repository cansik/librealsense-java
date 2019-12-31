package org.intel.rs.types;

public enum CameraInfo
{
    Name(0),
    SerialNumber(1),
    RecommendedFirmwareVersion(2),
    FirmwareVersion(3),
    PhysicalPort(4),
    DebugOpCode(5),
    AdvancedMode(6),
    ProductId(7),
    CameraLocked(8),
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
