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
public class TreeOperatorTest {
    
    TreeOperator t;
    
    public TreeOperatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         t = new TreeOperator();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of constructTree method, of class TreeOperator.
     */
    @Test
    public void testConstructTree() {
        byte[] data = {20, 10, 20, 20, 10};
        
        HuffmanNode expt = new HuffmanNode(new HuffmanNode((byte) 10, 2), new HuffmanNode((byte) 20, 3));
        HuffmanNode rest = t.constructTree(data);
        
        assertEquals(expt, rest);
    }
    
}
