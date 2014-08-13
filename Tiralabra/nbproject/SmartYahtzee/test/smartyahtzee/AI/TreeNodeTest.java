/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee.AI;

import java.util.Arrays;
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
public class TreeNodeTest {
    
    public TreeNodeTest() {
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
     * Test of getValue method, of class TreeNode.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        int[] dice = {1, 2, 3, 5};
        TreeNode instance = new TreeNode(dice);
        int[] expResult = {1, 2, 3, 5};
        int[] result = instance.getValue();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getChild method, of class TreeNode.
     */
    @Test
    public void testGetChild() {
        System.out.println("getChild");
        int[] dice = {1, 2, 4, 5};
        TreeNode instance = new TreeNode(dice);
        int[] expResult = {1, 2, 4, 5, 1};
        int[] result = instance.getChild().getValue();
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of getSibling method, of class TreeNode.
     */
    @Test
    public void testGetSibling() {
        System.out.println("getSibling");
        int[] dice = {1, 2, 4, 5};
        TreeNode instance = new TreeNode(dice);
        int[] expResult = {1, 2, 4, 5, 2};
        int[] result = instance.getChild().getSibling().getValue();
        assert(Arrays.equals(expResult, result));
    }
    
    @Test
    public void testGetChildTwoSteps() {
        System.out.println("getChild");
        int[] dice = {1, 2, 4};
        TreeNode instance = new TreeNode(dice);
        int[] expResult = {1, 2, 4, 1, 1};
        int[] result = instance.getChild().getChild().getValue();
        assert(Arrays.equals(expResult, result));
    }
    
    @Test
    public void testGetSiblingTwoSteps() {
        System.out.println("getSibling");
        int[] dice = {1, 2, 4};
        TreeNode instance = new TreeNode(dice);
        int[] expResult = {1, 2, 4, 2, 2};
        int[] result = instance.getChild().getSibling().getChild().getSibling().getValue();
        assert(Arrays.equals(expResult, result));
    }
    
}
