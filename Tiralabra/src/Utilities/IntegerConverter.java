/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 * Extracted from its original location in the encoder.
 *
 * @author virta
 */
public class IntegerConverter {

    /**
     * Decodes an integer from the bytearray given as parameter.
     *
     * @param array
     * @return an integer representation of the bytearray.
     */
    public static int ByteToInteger(byte[] array) {
        int returnInt = 0;
        int multiplier = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            returnInt |= ((array[i] & 0xFF) << multiplier * 8);
            multiplier++;
        }
        return returnInt;
    }

    /**
     * Takes any integer and produces a bytearray which represents the integer.
     *
     * @param key the key to be encoded in byte fashion.
     * @param byteCount the number of bytes it takes for the key.
     * @return a byte array representation of the integer.
     */
    public static byte[] IntegerToByte(int integer, int byteCount) {
        byte[] keyBytes = createByteArrayFromInt(byteCount, integer);

        keyBytes = removeLeadingZeros(keyBytes);

        return keyBytes;
    }

    /**
     * Creates a bytearray from the integer given as parameter.
     *
     * @param byteCount
     * @param key
     * @return
     */
    private static byte[] createByteArrayFromInt(int byteCount, int key) {
        byte[] keyBytes = new byte[byteCount];
        int index = 0;
        for (int i = keyBytes.length - 1; i >= 0; i--) {
            keyBytes[index] = (byte) ((key >> (8 * i)) & 0xFF);   //start from the maximum number of iterations to account for higher shifting.
            index++;
        }
        return keyBytes;
    }

    /**
     * Removes leadong zeros from a bytearray, they are not needed and take
     * space.
     *
     * @param array
     * @return
     */
    private static byte[] removeLeadingZeros(byte[] array) {
        int removeLeadingZerosIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                removeLeadingZerosIndex = i + 1;
            } else {
                break;
            }
        }
        byte[] newKey = new byte[array.length - removeLeadingZerosIndex];
        for (int i = 0; i < newKey.length; i++) {
            newKey[i] = array[removeLeadingZerosIndex];
            removeLeadingZerosIndex++;
        }
        return newKey;
    }

    /**
     * Calculates the approximate byte-width for the supplied integer.
     *
     * @param integer
     * @return
     */
    public static int getBytesPerInteger(int integer) {
        if (integer == 1) {
            return 1;
        }
        int bytes = 0;
        double k = integer;
        while (k > 1) {
            k /= 255;
            bytes++;
        }
        return bytes;
    }
    
}
