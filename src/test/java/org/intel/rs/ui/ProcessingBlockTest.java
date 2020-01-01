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

import static java.awt.image.BufferedImage.TYPE_3BYTE_BGR;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class ProcessingBlockTest {
    public static void main(String[] args) {
        new ProcessingBlockTest().runTest();
    }

    SimpleImageViewer viewer = new SimpleImageViewer();

    private Pipeline pipeline = new Pipeline();
    private Colorizer colorizer = new Colorizer();

    private volatile boolean running = true;

    BufferedImage colorImage = new BufferedImage(640, 480, TYPE_INT_RGB);

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
        DepthFrame depthFrame = frames.getDepthFrame();

        System.out.println("frames extracted!");

        VideoFrame colorizedDepth = colorizer.colorize(depthFrame);

        BufferedImage image = ImageUtils.createBufferedImage(colorFrame);
        viewer.display(image);

        colorizedDepth.release();
        frames.release();
    }
}
