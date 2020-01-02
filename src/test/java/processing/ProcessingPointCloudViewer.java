package processing;

import org.intel.rs.frame.DepthFrame;
import org.intel.rs.frame.FrameList;
import org.intel.rs.frame.Points;
import org.intel.rs.frame.VideoFrame;
import org.intel.rs.pipeline.Config;
import org.intel.rs.pipeline.Pipeline;
import org.intel.rs.pipeline.PipelineProfile;
import org.intel.rs.processing.Align;
import org.intel.rs.processing.DecimationFilter;
import org.intel.rs.processing.PointCloud;
import org.intel.rs.types.Format;
import org.intel.rs.types.Stream;
import org.intel.rs.types.Vertex;
import processing.core.PApplet;
import processing.core.PShape;

public class ProcessingPointCloudViewer extends PApplet {

    private Pipeline pipeline = new Pipeline();

    private PointCloud pointCloud = new PointCloud();
    private DecimationFilter decimationFilter = new DecimationFilter();

    private int cloudSize = 76800;
    private PShape cloud;

    @Override
    public void settings() {
        size(800, 600, P3D);
    }

    @Override
    public void setup() {
        // create camera
        Config cfg = new Config();
        cfg.enableStream(Stream.Depth, 640, 480);
        cfg.enableStream(Stream.Color, Format.Rgb8);

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
        for(int i = 0; i < cloudSize; i++) {
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

        // get points
        Points points = pointCloud.calculate(decimatedFrame);
        Vertex[] vertices = points.getVertices();

        points.release();
        decimatedFrame.release();
        frames.release();

        // update cloud
        for(int i = 0; i < cloudSize; i++) {
            Vertex v = vertices[i];
            cloud.setVertex(i, v.getX(), v.getY(), v.getZ());
        }

        // display cloud
        pushMatrix();
        translate(width / 2, height / 2);
        float scale = 180;
        scale(scale, scale, -scale);
        //rotateY(frameCount / 100f);
        //scale(1, 1, -1);
        shape(cloud);
        popMatrix();

        surface.setTitle("FPS: " + nfp(frameRate, 0,2));
    }

    @Override
    public void runSketch() {
        super.runSketch();
    }
}
