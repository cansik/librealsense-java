package org.intel.rs.ui;

import org.intel.rs.frame.Frame;
import org.intel.rs.frame.FrameList;
import org.intel.rs.frame.VideoFrame;
import org.intel.rs.pipeline.Config;
import org.intel.rs.pipeline.Pipeline;
import org.intel.rs.pipeline.PipelineProfile;
import org.intel.rs.processing.Colorizer;
import org.intel.rs.sensor.Sensor;
import org.intel.rs.types.Extension;
import org.intel.rs.types.Format;
import org.intel.rs.types.Stream;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

import static java.awt.image.BufferedImage.TYPE_3BYTE_BGR;
import static org.intel.rs.ui.Utils.readByteArrayToBufferedImage;
import static org.intel.rs.ui.Utils.readByteBufferToBufferedImage;

public class ProcessingBlockTest {
    public static void main(String[] args) {
        new ProcessingBlockTest().runTest();
    }

    SimpleImageViewer viewer = new SimpleImageViewer();

    private Pipeline pipeline = new Pipeline();
    private Colorizer colorizer = new Colorizer();

    private volatile boolean running = true;

    BufferedImage colorImage = new BufferedImage(640, 480, TYPE_3BYTE_BGR);

    public void runTest() {
        viewer.open(640, 480);
        viewer.display(colorImage);

        System.out.println("setting up camera...");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // shutdown camera
            running = false;

            pipeline.stop();
            System.out.println("camera has been shutdown!");
        }));

        // create camera
        Config cfg = new Config();
        cfg.enableStream(Stream.Depth, 640, 480);
        cfg.enableStream(Stream.Color, Format.Rgb8);

        PipelineProfile pp = pipeline.start(cfg);

        System.out.println("camera has been started!");

        // setting up thread to read data
        Thread thread = new Thread(() -> {
            while (running) {
                readFrames();
            }
        });
        thread.start();
    }

    public void readFrames() {
        FrameList frames = pipeline.waitForFrames();

        System.out.println("frames received!");

        VideoFrame colorFrame = frames.getColorFrame();
        VideoFrame depthFrame = frames.getDepthFrame();

        System.out.println("frames extracted!");

        VideoFrame colorizedDepth = colorizer.colorize(depthFrame);

        // copy frame data
        byte[] data = colorFrame.getManagedArray();
        colorImage = readByteArrayToBufferedImage(data);
        viewer.display(colorImage);

        frames.release();
    }
}
