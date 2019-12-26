package org.intel.rs.pipeline;

import org.bytedeco.librealsense2.rs2_config;
import org.bytedeco.librealsense2.rs2_error;
import org.bytedeco.librealsense2.rs2_pipeline_profile;
import org.intel.rs.types.Format;
import org.intel.rs.types.Stream;
import org.intel.rs.util.NativeDecorator;

import static org.bytedeco.librealsense2.global.realsense2.*;
import static org.intel.rs.util.RealSenseUtil.checkError;
import static org.intel.rs.util.RealSenseUtil.toBoolean;

public class Config implements NativeDecorator<rs2_config> {
    rs2_config instance;

    public Config(rs2_config instance) {
        this.instance = instance;
    }

    public Config() {
        rs2_error error = new rs2_error();
        instance = rs2_create_config(error);
        checkError(error);
    }

    public void enableStream(Stream stream) {
        enableStream(stream, -1);
    }

    public void enableStream(Stream stream, int index) {
        rs2_error error = new rs2_error();
        rs2_config_enable_stream(instance, stream.getIndex(), index, 0, 0, Format.Any.getIndex(), 0, error);
        checkError(error);
    }

    public void enableStream(Stream streamType, int streamIndex, int width, int height) {
        enableStream(streamType, streamIndex, width, height, Format.Any, 0);
    }

    public void enableStream(Stream streamType, int streamIndex, int width, int height, Format format) {
        enableStream(streamType, streamIndex, width, height, format, 0);
    }

    public void enableStream(Stream streamType, int streamIndex, int width, int height, Format format, int framerate) {
        rs2_error error = new rs2_error();
        rs2_config_enable_stream(instance, streamType.getIndex(), streamIndex, width, height, format.getIndex(), framerate, error);
        checkError(error);
    }

    public void enableStream(Stream streamType, int width, int height) {
        enableStream(streamType, width, height, Format.Any, 0);
    }

    public void enableStream(Stream streamType, int width, int height, Format format) {
        enableStream(streamType, width, height, format, 0);
    }

    public void enableStream(Stream streamType, int width, int height, Format format, int framerate) {
        rs2_error error = new rs2_error();
        rs2_config_enable_stream(instance, streamType.getIndex(), -1, width, height, format.getIndex(), framerate, error);
        checkError(error);
    }

    public void enableStream(Stream streamType, Format format) {
        enableStream(streamType, format, 0);
    }

    public void enableStream(Stream streamType, Format format, int framerate) {
        rs2_error error = new rs2_error();
        rs2_config_enable_stream(instance, streamType.getIndex(), -1, 0, 0, format.getIndex(), framerate, error);
        checkError(error);
    }

    public void enableStream(Stream streamType, int streamIndex, Format format) {
        enableStream(streamType, streamIndex, format, 0);
    }

    public void enableStream(Stream streamType, int streamIndex, Format format, int framerate) {
        rs2_error error = new rs2_error();
        rs2_config_enable_stream(instance, streamType.getIndex(), streamIndex, 0, 0, format.getIndex(), framerate, error);
        checkError(error);
    }

    public void enableAllStreams() {
        rs2_error error = new rs2_error();
        rs2_config_enable_all_stream(instance, error);
        checkError(error);
    }

    public void enableDevice(String serial) {
        rs2_error error = new rs2_error();
        rs2_config_enable_device(instance, serial, error);
        checkError(error);
    }

    public void enableDeviceFromFile(String filename) {
        rs2_error error = new rs2_error();
        rs2_config_enable_device_from_file(instance, filename, error);
        checkError(error);
    }

    public void enableRecordToFile(String filename) {
        rs2_error error = new rs2_error();
        rs2_config_enable_record_to_file(instance, filename, error);
        checkError(error);
    }

    public void disableStream(Stream stream) {
        disableStream(stream, -1);
    }

    public void disableStream(Stream stream, int index) {
        rs2_error error = new rs2_error();
        rs2_config_disable_indexed_stream(instance, stream.getIndex(), index, error);
        checkError(error);
    }

    public void disableAllStreams() {
        rs2_error error = new rs2_error();
        rs2_config_disable_all_streams(instance, error);
        checkError(error);
    }

    public boolean canResolve(Pipeline pipe) {
        rs2_error error = new rs2_error();
        int res = rs2_config_can_resolve(instance, pipe.instance, error);
        checkError(error);
        return toBoolean(res);
    }

    public PipelineProfile resolve(Pipeline pipe) {
        rs2_error error = new rs2_error();
        rs2_pipeline_profile res = rs2_config_resolve(instance, pipe.instance, error);
        checkError(error);
        return new PipelineProfile(res);
    }

    @Override
    public rs2_config getInstance() {
        return instance;
    }

    @Override
    public void release() {
        rs2_delete_config(instance);
    }
}