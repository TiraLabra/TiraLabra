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
 * @author essalmen
 */
public class TreeListTest {
    
    public TreeListTest() {
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
     * Test of getLength method, of class TreeList.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        int[] dice = {6, 5, 4};
        TreeList instance = new TreeList(8, dice, new boolean[17]);
        int expResult = 8;
        int result = instance.getLength();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTree method, of class TreeList.
     */
    @Test
    public void testGetTree() {
        System.out.println("getTree");
        int[] root = {6, 5, 4};
        TreeList instance = new TreeList(8, root, new boolean[17]);
        DecisionTree expResult = new DecisionTree(root, new boolean[17]);
        DecisionTree result = instance.getTree(root);
        assertEquals(expResult, result);

    }

    /**
     * Test of getBiggestEVtree method, of class TreeList.
     */
    @Test
    public void testGetBiggestEVtree() {
        System.out.println("getBiggestEVtree");
        int[] root = {1, 2, 3, 5, 5};
        TreeList instance = new TreeList(8, root, new boolean[17]);
        int[] rootDice = {5, 5};
        DecisionTree expResult = new DecisionTree(rootDice, new boolean[17]);
        DecisionTree result = instance.getBiggestEVtree();
        assertEquals(expResult, result);

    }
    
}
