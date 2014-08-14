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
<<<<<<< HEAD
 * @author iorena
=======
 * @author essalmen
>>>>>>> 9021f29edc306246c3bcb08021f6d1d17ede5df0
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
<<<<<<< HEAD
        DecisionTree instance = null;
        int[] expResult = null;
        int[] result = instance.getRoot();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
=======
        int[] dice = {1, 2, 3};
        DecisionTree instance = new DecisionTree(dice);
        int[] expResult = {1, 2 ,3};
        int[] result = instance.getRoot();
        assertArrayEquals(expResult, result);
>>>>>>> 9021f29edc306246c3bcb08021f6d1d17ede5df0
    }

    /**
     * Test of getEV method, of class DecisionTree.
     */
    @Test
    public void testGetEV() {
        System.out.println("getEV");
<<<<<<< HEAD
        DecisionTree instance = null;
        double expResult = 0.0;
        double result = instance.getEV();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
=======
        int [] dice = {1, 2, 4, 5};
        DecisionTree instance = new DecisionTree(dice);
        double expResult = 0.0;
        double result = instance.getEV();
        assertEquals(expResult, result, 0.0);
    }
    
}

>>>>>>> 9021f29edc306246c3bcb08021f6d1d17ede5df0
