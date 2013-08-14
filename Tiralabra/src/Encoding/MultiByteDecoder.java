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

    private int byteArrayToInt(byte[] b) {
        int returnInt = 0;
        int multiplier = 0;
        for (int i = b.length-1; i >= 0; i--) {
            returnInt |= ((b[i] & 0xFF) << multiplier*8);
            multiplier++;
        }
        return returnInt;
    }
}
