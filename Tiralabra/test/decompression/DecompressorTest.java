package decompression;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import decompression.Decompressor;
import compression.Packer;
import compression.Packer;
import datastructures.Paketti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joonaskylliainen
 */
public class DecompressorTest {
    
    Packer packer;
    
    public DecompressorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        packer = new Packer();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testaaDecompressoria() {
        
    }
//    @Test
//    public void doesDecompressorWork() {
//        Paketti paketti = packer.pack("kissa");
//        Decompressor dec = new Decompressor(paketti.getTree(), paketti.getText());
//        assertEquals("kissa", dec.unzip());
//    }
}
