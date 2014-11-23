/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package superpakkaussofta;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jouko
 */
public class HuffmanCompressorTest {
    
    HuffmanCompressor comp;
    HuffmanNode n;
    byte[] data;
    
    public HuffmanCompressorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        HuffmanNode n1 = new HuffmanNode((byte) 10, 1);
        HuffmanNode n2 = new HuffmanNode((byte) 20, 2);
        n = new HuffmanNode(n1, n2);
        
        comp = new HuffmanCompressor();
        
        data = new byte[3];
        data[0] = 20;
        data[1] = 10;
        data[2] = 20;
    }
    
    @After
    public void tearDown() {
        
    }

    /**
     * Test of compress method, of class HuffmanCompressor.
     */
    @Test
    public void testCompress() {
        byte[] expd = {5, 5};
        byte[] resd = comp.compress(data);
        
        assertArrayEquals(expd, resd);
    }

    /**
     * Test of concatTreeWithByteArray method.
     * Byte codes for characters:
     * 0 = 48
     * 1 = 49
     * 2 = 50
     * a = 97
     * b = 98
     * c = 99
     * 
     * String to be concatenated: 10a1b20a2bc
     * 
     */
    @Test
    public void testConcatTreeWithByteArray() {
        byte[] expd = {49, 48, 97, 49, 98, 50, 48, 97, 50, 98, 99, 20, 10, 20};
        byte[] resd = comp.concatTreeWithByteArray(data, n);
        
        assertArrayEquals(expd, resd);
    }
    
}
