package org.intel.rs.ui;

import org.intel.rs.frame.DepthFrame;
import org.intel.rs.frame.FrameList;
import org.intel.rs.frame.VideoFrame;
import org.intel.rs.option.CameraOption;
import org.intel.rs.pipeline.Config;
import org.intel.rs.pipeline.Pipeline;
import org.intel.rs.pipeline.PipelineProfile;
import org.intel.rs.processing.Align;
import org.intel.rs.processing.Colorizer;
import org.intel.rs.processing.DecimationFilter;
import org.intel.rs.types.Format;
import org.intel.rs.types.Option;
import org.intel.rs.types.Stream;

import java.awt.image.BufferedImage;

public class ProcessingBlockTest {
    public static void main(String[] args) {
        new ProcessingBlockTest().runTest();
    }

    SimpleImageViewer colorViewer = new SimpleImageViewer();
    SimpleImageViewer depthViewer = new SimpleImageViewer();

    private Align align = new Align(Stream.Color);
    private Pipeline pipeline = new Pipeline();
    private Colorizer colorizer = new Colorizer();
    private DecimationFilter decimationFilter = new DecimationFilter();

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

        // set color scheme settings
        CameraOption colorScheme = colorizer.getOptions().get(Option.ColorScheme);
        colorScheme.setValue(2);

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
        FrameList alignedFrames = align.process(frames);

        VideoFrame colorFrame = alignedFrames.getColorFrame();
        DepthFrame depthFrame = alignedFrames.getDepthFrame();

        DepthFrame decimatedFrame = decimationFilter.process(depthFrame);
        VideoFrame colorizedDepth = colorizer.colorize(decimatedFrame);

        BufferedImage colorImage = ImageUtils.createBufferedImage(colorFrame);
        BufferedImage depthImage = ImageUtils.createBufferedImage(colorizedDepth);

        colorViewer.display(colorImage);
        depthViewer.display(depthImage);

        decimatedFrame.release();
        colorizedDepth.release();
        alignedFrames.release();
        frames.release();
    }
}
