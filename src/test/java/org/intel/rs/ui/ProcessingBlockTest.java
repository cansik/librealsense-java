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

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

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

    int frameCount = 0;

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
        int[] pixels = colorFrame.getPixels();
        int width = colorFrame.getWidth();
        int height = colorFrame.getHeight();

        int[] bitMasks = new int[]{0xff0000, 0xff00, 0xff};
        SinglePixelPackedSampleModel sm = new SinglePixelPackedSampleModel(
                DataBuffer.TYPE_INT, width, height, bitMasks);
        DataBufferInt db = new DataBufferInt(pixels, pixels.length);
        WritableRaster wr = Raster.createWritableRaster(sm, db, new Point());
        BufferedImage image = new BufferedImage(new DirectColorModel(24, 0xff0000, 0x00ff00, 0x0000ff),
                wr, false, null);

        // store frame for debug
        File outputfile = new File("frame_" + frameCount++ + ".png");
        try {
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //colorImage = readByteArrayToBufferedImage(data);
        viewer.display(image);

        colorizedDepth.release();
        frames.release();
    }


}
