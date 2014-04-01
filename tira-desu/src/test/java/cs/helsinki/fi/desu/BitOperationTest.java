
package cs.helsinki.fi.desu;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.Assert;

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
    }

    /**
     * Test of extractBit method, of class BitOperation.
     */
    public void testExtractBit() {
    }

    /**
     * Test of extractMultipleBits method, of class BitOperation.
     */
    public void testExtractMultipleBits() {
    }

    /**
     * Test of rotateLeft method, of class BitOperation.
     */
    public void testRotateLeft() {
    }

    /**
     * Test of xor method, of class BitOperation.
     */
    public void testXor() {
        byte a[] = { (byte) 11001100 };
        byte b[] = { (byte) 11001100 };
        byte arr[] = { (byte) 11111111 };
        assertEquals(arr, bitOp.xor(a, b));
    }

    /**
     * Test of separateBytes method, of class BitOperation.
     */
    public void testSeparateBytes() {
    }

    /**
     * Test of concatBits method, of class BitOperation.
     */
    public void testConcatBits() {
    }
    
}
