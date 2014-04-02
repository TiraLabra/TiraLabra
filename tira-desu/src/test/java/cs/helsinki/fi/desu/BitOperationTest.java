
package cs.helsinki.fi.desu;

import java.util.Arrays;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * @author Juha Lindqvist <juha.lindqvist@cs.helsinki.fi>
 */

public class BitOperationTest {
    
    private BitOperation bitOp;
    
    public BitOperationTest() {
        bitOp = new BitOperation();
    }

    /**
     * Test of setBit method, of class BitOperation.
     */
    public void testSetBit() {
        byte[] test = "tesuing".getBytes();
         // translates to 01110100011001010111001101110101011010010110111001100111
         // set test string to "testing"                 ^ changes this bit to 0
        bitOp.setBit(test, 31, 0);
        
        byte[] result = "testing".getBytes();
        assertTrue(Arrays.equals(result, test));
    }
    
    /**
     * Test of setBit method, of class BitOperation.
     */
    public void testSetBitMultipleTimes() {
        byte[] test = "aaaa".getBytes();
        
        // set every letter from "a" to "b"
        for (int i = 0; i < 4; i++) {
            bitOp.setBit(test, 6 + i * 8, 1);
            bitOp.setBit(test, 7 + i * 8, 0);
        }
        
        byte[] result = "bbbb".getBytes();
        assertTrue(Arrays.equals(result, test));
    }

    /**
     * Test of extractBit method, of class BitOperation.
     */
    public void testExtractBit() {
        byte[] test = "testing".getBytes();
         // translates to 01110100011001010111001101110100011010010110111001100111
         //        bit to take is ^
        int result = bitOp.extractBit(test, 8);
        assertEquals(0, result);
    }

    /**
     * Test of extractMultipleBits method, of class BitOperation.
     */
    public void testExtractMultipleBits() {
        byte[] test = "aaaaaba".getBytes();
        byte[] result = "b".getBytes();
        
        //extract letter b from array
        byte[] extraction = bitOp.extractMultipleBits(test, 5 * 8, 8);
        assertTrue(Arrays.equals(extraction, result));
    }

    /**
     * Test of rotateLeft method, of class BitOperation.
     */
    public void testRotateLeft() {
        byte[] test = { 100, 6, 7, 8}; // 100 = 01100100
        byte result = -56;             // 56  = 11001000
        
        // rotate by one step for the whole array
        test = bitOp.rotateLeft(test, test.length * 8, 1);
        assertEquals(result, test[0]);
    }

    /**
     * Test of xor method, of class BitOperation.
     */
    public void testXor() {
        byte a[] = { 1 }; // 01 in binary
        byte b[] = { 2 }; // 10 in binary
        byte arr = 3;     // 11 in binary
        byte resArr[] = bitOp.xor(a, b);
        assertEquals(Byte.toString(arr), Byte.toString(resArr[0]));
    }
    
    /**
     * Test of xor method, of class BitOperation.
     */
    public void testXor2() {
        byte a[] = { -67 }; // 1011 1101 in binary
        byte b[] = { -1 };  // 1111 1111 in binary
        byte arr = 66;      // 0100 0010 in binary
        byte resArr[] = bitOp.xor(a, b);
        assertEquals(Byte.toString(arr), Byte.toString(resArr[0]));
    }

    /**
     * Test of separateBytes method, of class BitOperation.
     */
    public void testSeparateBytes() {
        byte[] test = {-1, 126, 127, 4};
        byte[] result = bitOp.separateBytes(test, 4);
        byte[] check = {-16, -16, 112, -32};
        
        System.out.println(Integer.toBinaryString((byte) test[0]));
        System.out.println(Integer.toBinaryString(test[1]));
        System.out.println(Integer.toBinaryString(test[2]));
        System.out.println(Integer.toBinaryString(test[3]));
        
        System.out.println(Integer.toBinaryString(result[0]));
        System.out.println(Integer.toBinaryString(result[1]));
        System.out.println(Integer.toBinaryString(result[2]));
        System.out.println(Integer.toBinaryString(result[3]));
        assertTrue(Arrays.equals(result, check));
    }

    /**
     * Test of concatBits method, of class BitOperation.
     */
    public void testConcatBits() {
        
    }
}
