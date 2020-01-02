package org.intel.rs.device;

public interface OnDevicesChangedCallback {
    void onChanged(DeviceList removed, DeviceList added);
}
