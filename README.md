# Intel RealSense Java
Intel® RealSense™ SDK 2 wrapper for Java.

Supported version: `v2.23.0`

This repository is based on the following works:

- https://github.com/cansik/librealsense/tree/master/wrappers/java
- https://github.com/edwinRNDR/librealsense
- https://github.com/cansik/realsense-processing

### Limitations

Currently, following methods are not generated with javacpp because they use callbacks which can not be auto-generated without javacpp info modifications:
- `rs2_devices_changed_callback_ptr`
- `rs2_pipeline_start_with_callback`
- `rs2_pipeline_start_with_config_and_callback`
- `rs2_set_notifications_callback`

- `rs2_devices_changed_callback_ptr`
- `rs2_pipeline_start_with_callback`
- `rs2_pipeline_start_with_config_and_callback`
- `rs2_set_notifications_callback`

### Build from source (currently just for MacOS)

1. Clone this repository
2. Run `getRealSenseRepo.sh` to download the librealsense from github
3. Run `buildRealSenseMacOS.sh` to build the realsense libs
3. Change the lib extension and os name in `generateNativeLib.sh` for your os.
4. Run `generateNativeLib.sh` to generate the native libs
