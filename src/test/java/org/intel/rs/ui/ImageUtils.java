package org.intel.rs.ui;

import org.intel.rs.frame.VideoFrame;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;

public class ImageUtils {
    private static int[] bandOffsets1D = new int[]{0};
    private static int[] bandOffsets3D = new int[]{0, 1, 2};

    public static BufferedImage createBufferedImage(VideoFrame frame) {
        int height = frame.getHeight();
        int width = frame.getWidth();
        int scanLineStride = frame.getStride();
        int pixelStride = scanLineStride / width;

        int[] bandOffsets = (pixelStride == 3) ? bandOffsets3D : bandOffsets1D;
        int colorSpace = (pixelStride == 3) ? ColorSpace.CS_sRGB : ColorSpace.CS_GRAY;

        return createBufferedImage(frame.getBytes(),
                width,
                height,
                pixelStride,
                scanLineStride,
                bandOffsets,
                colorSpace
        );
    }

    public static BufferedImage createBufferedImage(byte[] data, int width, int height, int pixelStride, int scanLineStride, int[] bandOffsets, int colorSpace) {
        ComponentSampleModel sm = new ComponentSampleModel(DataBuffer.TYPE_BYTE, width, height, pixelStride, scanLineStride, bandOffsets);
        DataBufferByte db = new DataBufferByte(data, data.length);

        WritableRaster wr = Raster.createWritableRaster(sm, db, new Point());
        ColorSpace cs = ColorSpace.getInstance(colorSpace);
        ColorModel cm = new ComponentColorModel(cs, false, false, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);

        return new BufferedImage(cm, wr, false, null);
    }
}
