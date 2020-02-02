package processing;

import org.intel.rs.frame.DepthFrame;
import org.intel.rs.frame.FrameList;
import org.intel.rs.frame.Points;
import org.intel.rs.frame.VideoFrame;
import org.intel.rs.pipeline.Config;
import org.intel.rs.pipeline.Pipeline;
import org.intel.rs.pipeline.PipelineProfile;
import org.intel.rs.processing.DecimationFilter;
import org.intel.rs.processing.PointCloud;
import org.intel.rs.types.Format;
import org.intel.rs.types.Pixel;
import org.intel.rs.types.Stream;
import org.intel.rs.types.Vertex;
import processing.core.PApplet;
import processing.core.PShape;

import java.nio.ByteBuffer;

public class ProcessingPointCloudViewer extends PApplet {

    private Pipeline pipeline = new Pipeline();

    private PointCloud pointCloud = new PointCloud();
    private DecimationFilter decimationFilter = new DecimationFilter();

    private int streamWidth = 424;
    private int streamHeight = 240;

    private PShape cloud;

    @Override
    public void settings() {
        size(800, 600, P3D);
    }

    @Override
    public void setup() {
        // create camera
        Config cfg = new Config();
        cfg.enableStream(Stream.Depth, streamWidth, streamHeight);
        //cfg.enableStream(Stream.Color, streamWidth, streamHeight);

        PipelineProfile pp = pipeline.start(cfg);

        // setup shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            pipeline.stop();
            System.out.println("camera has been shutdown!");
        }));

        // create cloud
        cloud = createShape();
        cloud.setStroke(color(255));
        cloud.beginShape(POINTS);
        for(int i = 0; i < streamWidth * streamHeight * 0.25; i++) {
            cloud.vertex(0, 0, 0);
        }
        cloud.endShape();
    }

    @Override
    public void draw() {
        background(0);

        // get frames
        FrameList frames = pipeline.waitForFrames();
        DepthFrame depthFrame = frames.getDepthFrame();
        DepthFrame decimatedFrame = decimationFilter.process(depthFrame);

        // calculate points
        Points points = pointCloud.calculate(decimatedFrame);

        // copy data
        Vertex[] vertices = points.getVertices();

        points.release();
        decimatedFrame.release();
        frames.release();

        // update cloud
        for(int i = 0; i < vertices.length; i++) {
            Vertex v = vertices[i];
            cloud.setVertex(i, v.getX(), v.getY(), v.getZ());
        }

        // display cloud
        pushMatrix();
        translate(width * 0.5f, height * 0.5f);
        float scale = 180;
        scale(scale, scale, -scale);
        rotateY(frameCount / 100f);
        //scale(1, 1, -1);
        shape(cloud);
        popMatrix();

        surface.setTitle("FPS: " + nfp(frameRate, 0,2));
    }

    @Override
    public void runSketch() {
        super.runSketch();
    }

    public static void main(String... args) {
        ProcessingPointCloudViewer viewer = new ProcessingPointCloudViewer();
        viewer.runSketch();
    }
}
