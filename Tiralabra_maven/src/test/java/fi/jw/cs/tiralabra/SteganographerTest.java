package fi.jw.cs.tiralabra;

import junit.framework.*;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-07
 */
public class SteganographerTest extends TestCase {
    Steganographer stego;

    public void setUp() throws Exception {


    }

    public void testEncode() throws Exception {

    }

    public void testCheckMessageSize() throws Exception {

    }

    public void testGetMaximumMessageLength() throws Exception {

    }

    public void testWriteMessageLength() throws Exception {

    }

    public void testReadMessageLength() throws Exception {

    }

    public void testRemoveAlphaChannel() throws Exception {
        //aaaarrrrggggbbbb -- a = alpha, rgb;

        int color = 0b11111111111111111111111111111111;
        int expected = 0b00000000111111111111111111111111;
        int noalpha = Steganographer.removeAlphaChannel(color);
        assertEquals(expected, noalpha);


    }

    public void testGetChar() throws Exception {
        int[] ones = new int[]{
                0b1, 0b101, 0b11, 0b1101, 0b11111111111111111111111111111111
        };

        int[] zeroes = new int[]{
                0b0, 0b10, 0b110, 0b11111111111111111111111111111110
        };

        for (int i : ones) {
            assertEquals('1', Steganographer.getChar(i));
        }
        for (int i : zeroes) {
            assertEquals('0', Steganographer.getChar(i));
        }

    }

    public void testSetSteganoBit() throws Exception {

    }
}
