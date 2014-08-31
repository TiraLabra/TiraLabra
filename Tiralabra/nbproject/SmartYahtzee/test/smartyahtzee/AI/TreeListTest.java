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
        int[] dice = {6, 5, 4, 2, 1};
        TreeList instance = new TreeList(8, dice, new boolean[17]);
        int expResult = 11;
        int result = instance.getLength();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTree method, of class TreeList.
     */
    @Test
    public void testGetTree() {         //noppien pitää olla groupingsort-järjestyksessä :<
        System.out.println("getTree");
        int[] dice = {4, 4, 6, 5, 1};
        int[] root = {4, 4, 6};
        TreeList instance = new TreeList(8, dice, new boolean[17]);
        DecisionTree expResult = new DecisionTree(root, new boolean[17]);
        DecisionTree result = instance.getTree(root);
        assertArrayEquals(expResult.getRoot(), result.getRoot());

        
    }
    
    @Test
    public void testGetTree2() {            //testataan ei-lineaarisesti generoituvan kombinaation löytymistä
        System.out.println("getTree");
        int[] dice = {4, 4, 6, 5, 1};
        int[] root = {6};
        TreeList instance = new TreeList(8, dice, new boolean[17]);
        DecisionTree expResult = new DecisionTree(root, new boolean[17]);
        DecisionTree result = instance.getTree(root);
        assertArrayEquals(root, result.getRoot());

        
    }
    
    @Test
    public void testGetTree3() {            //testataan ei-lineaarisesti generoituvan kombinaation löytymistä
        System.out.println("getTree");
        int[] dice = {5, 5, 6, 4, 3};
        int[] root = {3, 4};
        TreeList instance = new TreeList(8, dice, new boolean[17]);
        DecisionTree expResult = new DecisionTree(root, new boolean[17]);
        DecisionTree result = instance.getTree(root);
        assertArrayEquals(root, result.getRoot());

        
    }

    /**
     * Test of getBiggestEVtree method, of class TreeList.
     */
    @Test
    public void testGetBiggestEVtree() {
        System.out.println("getBiggestEVtree");
        int[] root = {5, 5, 3, 2, 1};
        TreeList instance = new TreeList(8, root, new boolean[17]);
        int[] rootDice = {5, 5};
        DecisionTree expResultTree = new DecisionTree(rootDice, new boolean[17]);
        double expResult = expResultTree.getEV();
        DecisionTree resultTree = instance.getBiggestEVtree();
        double result = resultTree.getEV();
        assertEquals(expResult, result, 0.05);

    }
    
    @Test
    public void testNoNullTrees() {
        System.out.println("noNullTrees");
        int[] dice = {6, 6, 4, 2, 1};
        TreeList instance = new TreeList(8, dice, new boolean[17]);
        DecisionTree[] trees = instance.getTrees();
        for (int i = 0; i < trees.length; i++)
        {
            assertTrue(trees[i] != null);
        }
        
    }
    
    @Test
    public void testNoTreesWithNullRoot() {
        System.out.println("noNullTrees");
        int[] dice = {6, 6, 4, 2, 1};
        TreeList instance = new TreeList(8, dice, new boolean[17]);
        DecisionTree[] trees = instance.getTrees();
        for (int i = 0; i < trees.length; i++)
        {
            assertTrue(trees[i].getRoot().length > 0);
        }
        
    }
    
}
