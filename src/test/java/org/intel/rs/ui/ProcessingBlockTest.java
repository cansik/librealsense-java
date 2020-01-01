package org.intel.rs.ui;

import org.intel.rs.frame.DepthFrame;
import org.intel.rs.frame.FrameList;
import org.intel.rs.frame.VideoFrame;
import org.intel.rs.pipeline.Config;
import org.intel.rs.pipeline.Pipeline;
import org.intel.rs.pipeline.PipelineProfile;
import org.intel.rs.processing.Colorizer;
import org.intel.rs.types.Format;
import org.intel.rs.types.Stream;

import java.awt.image.BufferedImage;

public class ProcessingBlockTest {
    public static void main(String[] args) {
        new ProcessingBlockTest().runTest();
    }

    SimpleImageViewer colorViewer = new SimpleImageViewer();
    SimpleImageViewer depthViewer = new SimpleImageViewer();

    private Pipeline pipeline = new Pipeline();
    private Colorizer colorizer = new Colorizer();

    private volatile boolean running = true;

    public void runTest() {
        colorViewer.open(640, 480, "Color");
        depthViewer.open(640, 480, "Depth");

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

        VideoFrame colorFrame = frames.getColorFrame();
        DepthFrame depthFrame = frames.getDepthFrame();

        VideoFrame colorizedDepth = colorizer.colorize(depthFrame);

        BufferedImage colorImage = ImageUtils.createBufferedImage(colorFrame);
        BufferedImage depthImage = ImageUtils.createBufferedImage(colorizedDepth);

        colorViewer.display(colorImage);
        depthViewer.display(depthImage);

        colorizedDepth.release();
        frames.release();
    }
}
