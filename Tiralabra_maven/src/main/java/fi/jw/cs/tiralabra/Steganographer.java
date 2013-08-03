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

    public static final int BITS_PER_PIXEL = 3; // cannot assume alpha
    private File file;
    private BufferedImage image;
    private String message;
    private int width;
    private int height;
    private int messageLength;

    public Steganographer(String path) throws IOException {
        this(path, "");
    }

    public Steganographer(String path, String message) throws IOException {
        file = new File(path);
        image = ImageIO.read(file);
        width = image.getWidth();
        height = image.getHeight();
        this.message = message;
        messageLength = message.length();

    }

    public void encode() {
        checkMessageSize();
        encodeBits();
    }

    public void checkMessageSize() throws IllegalArgumentException {

        int maxBits = width * height * BITS_PER_PIXEL;

        if (messageLength > maxBits) {
            throw new IllegalArgumentException("Trying to stuff " + messageLength + " bits to a picture with just " + maxBits + " bits available for encoding");
        }

    }

    protected void encodeMessageLength(int x, int y) {
        Color msgLen = new Color(messageLength);
        int len = removeAlphaChannel(msgLen.getRGB());
        image.setRGB(0, 0, len);
    }

    protected int readMessageLength(int x, int y) {
        return removeAlphaChannel(image.getRGB(x, y)); // has alpha channel again;

    }

    protected int removeAlphaChannel(int color) {
        return ((color << 8) >> 8);
    }

    protected char getChar(int color) {
        if ((color & 1) == 1)
            return '1';
        else
            return '0';
    }

    protected void decodeBits() {
        messageLength = readMessageLength(0, 0);
        message = "";
        int bitsRead = 0;
        for (int w = 1; w < width; w++) {
            for (int h = 0; h < height; h++) {
                if (bitsRead < messageLength) {

                    Color c = new Color(image.getRGB(w, h));
                    int red = c.getRed();
                    int green = c.getGreen();
                    int blue = c.getBlue();
                    int alpha = c.getAlpha();

                    message += getChar(red);
                    bitsRead++;

                    if (bitsRead < messageLength) {
                        message += getChar(green);
                        bitsRead++;
                    }
                    if (bitsRead < messageLength) {
                        message += getChar(blue);
                        bitsRead++;
                    }
                }
            }
        }
    }

    protected void encodeBits() {

        encodeMessageLength(0, 0);

        char[] chars = message.toCharArray();
        int charsWritten = 0;
        for (int w = 1; w < width; w++) {
            for (int h = 0; h < height; h++) {
                if (charsWritten < messageLength) {
                    char r = chars[charsWritten++];
                    char g = (charsWritten < messageLength) ? chars[charsWritten++] : '0';
                    char b = (charsWritten < messageLength) ? chars[charsWritten++] : '0';

                    Color c = new Color(image.getRGB(w, h));
                    int red = c.getRed();
                    int green = c.getGreen();
                    int blue = c.getBlue();
                    int alpha = c.getAlpha();


                    // 0xfe = 254 = 0b11111110 => ANDing will clear the LSB
                    red &= 0xfe;
                    green &= 0xfe;
                    blue &= 0xfe;

                    if (r == '1')
                        red |= 1;

                    if (g == '1')
                        green |= 1;

                    if (b == '1')
                        blue |= 1;

                    Color n = new Color(red, green, blue, alpha);
                    image.setRGB(w, h, n.getRGB());
                }
            }
        }
    }

    public void emptyLeastSignificantBits() {

        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                Color c = new Color(image.getRGB(w, h));
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();
                int alpha = c.getAlpha();

                // 0xfe = 254 = 0b11111110 => ANDing will clear the LSB
                red &= 0xfe;
                green &= 0xfe;
                blue &= 0xfe;

                Color n = new Color(red, green, blue, alpha);
                image.setRGB(w, h, n.getRGB());
            }
        }
    }

    public void saveFile(String path) throws IOException {
        File f = new File(path);
        ImageIO.write(image, "png", f);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
