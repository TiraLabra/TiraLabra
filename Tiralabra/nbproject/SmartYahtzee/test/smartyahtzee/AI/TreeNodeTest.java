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
        TreeNode instance = null;
        int[] expResult = null;
        int[] result = instance.getValue();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChild method, of class TreeNode.
     */
    @Test
    public void testGetChild() {
        System.out.println("getChild");
        TreeNode instance = null;
        TreeNode expResult = null;
        TreeNode result = instance.getChild();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSibling method, of class TreeNode.
     */
    @Test
    public void testGetSibling() {
        System.out.println("getSibling");
        TreeNode instance = null;
        TreeNode expResult = null;
        TreeNode result = instance.getSibling();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
