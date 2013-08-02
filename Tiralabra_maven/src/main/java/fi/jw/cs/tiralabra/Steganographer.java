package fi.jw.cs.tiralabra;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-01
 */

public class Steganographer {
    private File file;
    private BufferedImage image;
    private String message;

    public Steganographer(String path, String message) throws IOException {
        file = new File(path);
        image = ImageIO.read(file);
    }

    public void emptyLeastSignificantBits() {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                Color c = new Color(image.getRGB(w, h));
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();

                // 0xfe = 254 = 0b11111110 => ANDing will clear the LSB
                red &= 0xfe;
                green &= 0xfe;
                blue &= 0xfe;

                image.setRGB(w, h, c.getRGB());
            }
        }
    }

    public void saveFile(String path) throws IOException {
        File f = new File(path);
        ImageIO.write(image, "png", f);
    }
}
