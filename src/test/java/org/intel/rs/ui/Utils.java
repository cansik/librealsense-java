package org.intel.rs.ui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Utils {
    public static BufferedImage readByteBufferToBufferedImage(ByteBuffer byteBuffer)
    {
        if(null == byteBuffer)
            return null;

        byte[] data = byteBuffer.array();
        return readByteArrayToBufferedImage(data);
    }

    public static BufferedImage readByteArrayToBufferedImage(byte[] data) {
        ByteArrayInputStream in = new ByteArrayInputStream(data);

        BufferedImage image = null;
        try {
            image = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
