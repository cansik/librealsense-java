#!/usr/bin/env bash

# dependencies
brew install cmake libusb pkg-config
brew cask install vulkan-sdk

# go to repo
cd librealsense
mkdir build && cd build
sudo xcode-select --reset
cmake .. -DBUILD_EXAMPLES=true -DBUILD_WITH_OPENMP=false -DHWM_OVER_XU=false
make -j