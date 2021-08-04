# Intel RealSense Java [![](https://jitpack.io/v/cansik/librealsense-java.svg)](https://jitpack.io/#cansik/librealsense-java)
Intel® RealSense™ SDK 2 wrapper for Java.

Supported version: `v2.44.0`

Following binaries are pre-compiled within the release jar:

- Windows x86 / x64
- Mac x64
- Linux armHF / arm64 / x86 / x64

#### Important ⚠️
- The library is still under development
- Not all methods have been ported yet to fully support `2.44.0` through the high level API

### Gradle / Maven
Include the library directly into your gradle / maven build by using [jitpack](https://jitpack.io/#cansik/librealsense-java).

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.cansik:librealsense-java:2.44.0-0.4.2'
}
```

### Examples

There are examples in the test folder for java & AWT and Processing:

- Java
  - [Viewer Example](https://github.com/cansik/librealsense-java/blob/master/src/test/java/org/intel/rs/ui/SimpleImageViewer.java)
  - [Processing Block Example](https://github.com/cansik/librealsense-java/blob/master/src/test/java/org/intel/rs/ui/ProcessingBlockTest.java)
- Processing
  - [Processing Viewer Example](https://github.com/cansik/librealsense-java/blob/master/src/test/java/processing/ProcessingViewer.java)
  - [Processing PointCloud Example](https://github.com/cansik/librealsense-java/blob/master/src/test/java/processing/ProcessingPointCloudViewer.java)

### Library

The library is based on the librealsense2 C-warpper which has been ported by [Samuel Audet](https://github.com/saudet).

This repository is based on the following works:

- [cansik/librealsense](https://github.com/cansik/librealsense/tree/master/wrappers/java)
- [edwinRNDR/librealsense](https://github.com/edwinRNDR/librealsense)
- [cansik/realsense-processing](https://github.com/cansik/realsense-processing)
