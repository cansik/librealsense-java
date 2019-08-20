#!/usr/bin/env bash

# configure these for your os
platformName="macosx-x86_64" # linux-x86_64
platformLibEnding="dylib" # so

# default values
libName="RealSense"
apiDir="org/intel/rs/api"
javaSrc="src/main/java"
workingDir=$(pwd)
jniLibFile="libjni${libName}.${platformLibEnding}"

cd "$javaSrc"

javac -cp "${workingDir}/javacpp.jar" "${apiDir}/${libName}LibraryConfig.java"
java -jar "${workingDir}/javacpp.jar" "${apiDir}/${libName}LibraryConfig"
javac -cp "${workingDir}/javacpp.jar":. "${apiDir}/${libName}.java" # has to be different on windows (;)
java -jar "${workingDir}/javacpp.jar" "${apiDir}/${libName}" -Xcompiler -I${workingDir}/librealsense/include/librealsense2 -Xcompiler -lpthread

# copy native file (maybe not)
nativeLibFolder="${workingDir}/native/${platformName}/"
mkdir -p "${nativeLibFolder}"
cp -f "${apiDir}/${platformName}/${jniLibFile}" "${nativeLibFolder}/${jniLibFile}"

# cleanup
rm "${apiDir}/*.class"
#rm -rf "${apiDir}/${platformName}"