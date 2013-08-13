/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Encoding;

/**
 *
 * @author virta
 */
public class MultiByteDecoder {

    private static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF
                | (b[2] & 0xFF) << 8
                | (b[1] & 0xFF) << 16
                | (b[0] & 0xFF) << 24;
    }
}
