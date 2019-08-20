#!/usr/bin/env bash
version="v2.23.0"

git clone https://github.com/IntelRealSense/librealsense.git
cd librealsense
git fetch && git fetch --tags
git checkout "$version"