/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee.AI;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iorena
 */
public class DecisionTreeTest {
    
    public DecisionTreeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRoot method, of class DecisionTree.
     */
    @Test
    public void testGetRoot() {
        System.out.println("getRoot");
        DecisionTree instance = null;
        int[] expResult = null;
        int[] result = instance.getRoot();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEV method, of class DecisionTree.
     */
    @Test
    public void testGetEV() {
        System.out.println("getEV");
        DecisionTree instance = null;
        double expResult = 0.0;
        double result = instance.getEV();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
