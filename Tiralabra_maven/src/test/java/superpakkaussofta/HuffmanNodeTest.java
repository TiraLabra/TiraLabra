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
public class HuffmanNodeTest {
    
    HuffmanNode n;
    
    public HuffmanNodeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        HuffmanNode n1 = new HuffmanNode((byte) 10, 2);
        HuffmanNode n2 = new HuffmanNode((byte) 20, 3);
        n = new HuffmanNode(n1, n2);
    }
    
    @After
    public void tearDown() {
    }
    /**
     * Testing that equals method returns true when equal!
     */
    @Test
    public void testEqualsEqualNodes(){
        HuffmanNode n1 = new HuffmanNode((byte) 10, 2);
        HuffmanNode n2 = new HuffmanNode((byte) 20, 3);
        HuffmanNode nexp = new HuffmanNode(n1, n2);
        
        assertTrue(n.equals(nexp));
    }
    /**
     * Testing that equals method false true when not equal!
     */
    @Test
    public void testEqualsNotEqualNodes(){
        HuffmanNode n1 = new HuffmanNode((byte) 10, 2);
        HuffmanNode n2 = new HuffmanNode((byte) 20, 4);
        HuffmanNode nexp = new HuffmanNode(n1, n2);
        
        assertFalse(n.equals(nexp));
    }

    /**
     * Test of toString method (and also reqToString).
     * 
     */
    @Test
    public void testToString() {
        assertEquals("10a2b20a3b", n.toString());
    }
}
