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
        int[] dice = {1, 2, 3};
        DecisionTree instance = new DecisionTree(dice, new boolean[17]);
        int[] expResult = {1, 2 ,3};
        int[] result = instance.getRoot();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getEV method, of class DecisionTree.
     */
    @Test
    public void testGetEV() {
        System.out.println("getEV");
        int [] dice = {1, 2, 4, 5};
        DecisionTree instance = new DecisionTree(dice, new boolean[17]);
        double result = instance.getEV();
        assert(result > 0.0);
    }
    
    @Test
    public void testFindNode()
    {
        System.out.println("findNode");
        int[] root = {6, 6, 5}; 
        int[] expResult = {6, 6, 5, 3};
        DecisionTree instance = new DecisionTree(root, new boolean[17]);
        TreeNode foundNode = instance.findNode(3);
        assertArrayEquals(expResult, foundNode.getValue());
    }
    
    
    
    
}

