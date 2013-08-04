package fi.jw.cs.tiralabra;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-01
 */

public class Steganographer {

    public static final int BITS_PER_PIXEL = 3; // alpha is discarded
    private BufferedImage image;
    private String message;
    private int width;
    private int height;
    private int messageLength;

    public Steganographer(String path) throws IOException {
        this(path, "");
    }

    public Steganographer(String path, String msg) throws IOException {
        File file = new File(path);
        image = ImageIO.read(file);
        width = image.getWidth();
        height = image.getHeight();
        message = msg;
        messageLength = message.length();

    }

    public void encode() {
        checkMessageSize();
        encodeBits();
    }

    protected void checkMessageSize() throws IllegalArgumentException {

        int maxBits = getMaximumMessageLength();

        if (messageLength > maxBits) {
            throw new IllegalArgumentException("Trying to stuff " + messageLength + " bits to a picture with just " + maxBits + " bits available for encoding");
        }

    }

    public int getMaximumMessageLength() {
        return (width * height * BITS_PER_PIXEL) / 8; // in bytes.
    }

    protected void writeMessageLength(int x, int y) {
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

        writeMessageLength(0, 0);

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


                    red = setSteganoBit(red, r);
                    green = setSteganoBit(green, g);
                    blue = setSteganoBit(blue, b);

                    Color n = new Color(red, green, blue, alpha);
                    image.setRGB(w, h, n.getRGB());
                }
            }
        }
    }

    /**
     * First zero the least significant bit by ANDing it with 0xfe = 0b11111110 which will retain all but the last bit.<br/>
     * Then set the bit if the character calls for it.
     *
     * @param colorChannel int channel from a java.awt.Color object
     * @param bit          char that can be either '0' or '1'
     * @return int value after the given operations.
     */
    protected int setSteganoBit(int colorChannel, char bit) {
        int or = (bit == '1') ? 1 : 0;
        return ((colorChannel &= 0xfe) | or);
    }

    /**
     * Saves the current image into
     *
     * @param path
     * @throws IOException
     */
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
