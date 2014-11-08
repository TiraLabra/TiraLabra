/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package superpakkaussofta;

import junit.framework.TestCase;
import static org.junit.Assert.assertArrayEquals;

/**
 *
 * @author Jouko
 */
public class HuffmanCompressorTest extends TestCase {
    
    byte[] data;
    HuffmanCompressor comp;
    
    @Override
    protected void setUp() throws Exception {
        comp = new HuffmanCompressor();
        //data = {10, 20, 10, 30, 20, 10};
    }
    
    @Override
    protected void tearDown() throws Exception {
    }

    /**
     * Test of compress method, of class HuffmanCompressor.
     */
    public void testCompress() {
        // TODO
        fail("Method not yes testable.");
    }

    /**
     * Test of createHuffmanTree method, of class HuffmanCompressor.
     */
    public void testCreateHuffmanTree() {
        // TODO
        fail("Method not yes testable.");
    }

    /**
     * Test of copyAndSortByteArray method, of class HuffmanCompressor.
     */
    public void testCopyAndSortByteArray() {
        
        byte[] data = {10, 20, 10, 30, 20, 10};
        byte[] expResult = {10, 10, 10, 20, 20, 30};
        byte[] result = comp.copyAndSortByteArray(data);
        assertArrayEquals(expResult, result);
        
    }
    
}
