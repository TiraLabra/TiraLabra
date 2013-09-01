package fi.jw.cs.tiralabra;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static fi.jw.cs.tiralabra.Logger.log;

/**
 * Encodes a series of 1s and 0s into an image's R, G and B channels into the least significant bit
 * <p/>
 * Currently only supports writing in PNG format so that the image length can be deterministically
 * be read from the 0,0 position pixel.
 * <p/>
 * The maximum message length is the pixel count divided by <code>>BITS_PER_PIXEL</code which is currently
 * hard-coded as 3 since there are 3 channels we can require to be available: red, green and blue.
 * <p/>
 * <p>
 * <strong>Example use</strong>
 * </p>
 * <p/>
 * <pre>
 *     String binaryMessage = "101010101101011111000011100011001"; // For example from Huffman encoding :)
 *     String sourceFile = "/path/to/file.jpg";
 *     String destination = "/path/to/output.png"; //Note that the format will be PNG even if filename would end as .jpg
 *     Steganographer s = new Steganographer(sourceFile, binaryMessage);
 *     s.encode();
 *     s.saveFile(destination);
 * </pre>
 * <p>
 * The saved file should be visually nigh indistinguishable from the original. To recover the hidden binary string:
 * </p>
 * <pre>
 *     Steganographer dec = new Steganographer(pathToFilename);
 *     dec.decode();
 *     String originalBinaryString = dec.getMessage();
 * </pre>
 *
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-01
 */

public class Steganographer {

    public static final int BITS_PER_PIXEL = 3; // alpha is discarded
    public static final int BITS_PER_CHAR = 8;
    private BufferedImage image;
    private String message;
    private int width;
    private int height;
    private int messageLength;
    private String path;

    public Steganographer() {

    }

    public Steganographer(String path) throws IOException {
        this(path, "");
    }

    public Steganographer(String path, String msg) throws IOException {
        this.path = path;
        File file = new File(path);
        image = ImageIO.read(file);
        width = image.getWidth();
        height = image.getHeight();
        message = msg;
        messageLength = message.length();

    }

    public void encode() throws IOException {
        ensureFileHandles();
        checkMessageSize();
        encodeBits();
    }

    public void decode() throws IOException {
        ensureFileHandles();
        log("Stegano decoding bits");
        decodeBits();
    }

    private void ensureFileHandles() throws java.io.IOException {
        if (path == null || path.length() == 0) {
            throw new IllegalArgumentException("No path defined");
        }

        if (image == null) {
            image = ImageIO.read(new File(path));
        }
    }

    protected void checkMessageSize() throws IllegalArgumentException {

        int maxBits = getMaximumMessageLength()[0];

        if (messageLength > maxBits) {
            throw new IllegalArgumentException("Trying to stuff " + messageLength + " bits to a picture with just " + maxBits + " bits available for encoding");
        }

    }


    public int[] getMaximumMessageLength() {
        int totalBits = (width * height * BITS_PER_PIXEL);
        int totalChars = totalBits / BITS_PER_CHAR;
        int[] max = new int[2];
        max[0] = totalBits;
        max[1] = totalChars;
        return max;
    }

    /**
     * Writes the message length as an int as the first pixel's color value.
     */
    protected void writeMessageLength() {
        Color msgLen = new Color(messageLength);
        int len = removeAlphaChannel(msgLen.getRGB());
        image.setRGB(0, 0, len);
    }

    /**
     * Read message length from the first pixel of the image. Remove alpha channel so that we get the correct original int.
     *
     * @return
     */
    protected int readMessageLength() {
        return removeAlphaChannel(image.getRGB(0, 0)); // has alpha channel again;
    }

    /**
     * In a <code>Color</code> the 8 most significant bits are the alpha channel after which come RGB
     *
     * @param color int from a <code>java.awt.Color.getRGB()</code> call
     * @return int that you can write with <code>java.awt.image.BufferedImage.setRGB()</code>
     */
    protected static int removeAlphaChannel(final int color) {
        return ((color << 8) >>> 8);
    }

    /**
     * Return char matching the least significant bit of the integer
     *
     * @param color value from <code>getRGB()</code>
     * @return '1' or '0'
     */
    protected static char getChar(int color) {
        if ((color & 1) == 1)
            return '1';
        else
            return '0';
    }

    /**
     * Go through the image until <code>messageLength</code> bits have been read in from r, g and b channels in that order.
     */
    protected void decodeBits() {
        messageLength = readMessageLength();
        message = "";
        StringBuilder sb = new StringBuilder();
        int bitsRead = 0;
        for (int w = 1; w < width; w++) {
            for (int h = 0; h < height; h++) {
                if (bitsRead < messageLength) {

                    Color c = new Color(image.getRGB(w, h));
                    int red = c.getRed();
                    int green = c.getGreen();
                    int blue = c.getBlue();

                    sb.append(getChar(red));
                    bitsRead++;

                    if (bitsRead < messageLength) {
                        sb.append(getChar(green));
                        bitsRead++;
                    }
                    if (bitsRead < messageLength) {
                        sb.append(getChar(blue));
                        bitsRead++;
                    }
                }
            }
        }
        message = sb.toString();
    }

    /**
     * Encode the 'binary' string into the pixels' r, g and b channels in that order.
     */
    protected void encodeBits() {

        writeMessageLength();

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

    public void setPath(String path) {
        this.path = path;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
