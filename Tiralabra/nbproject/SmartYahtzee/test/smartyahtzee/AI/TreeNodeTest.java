/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee.AI;

<<<<<<< HEAD
=======
import java.util.Arrays;
>>>>>>> 9021f29edc306246c3bcb08021f6d1d17ede5df0
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
<<<<<<< HEAD
        TreeNode instance = null;
        int[] expResult = null;
        int[] result = instance.getValue();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
=======
        int[] dice = {1, 2, 3, 5};
        TreeNode instance = new TreeNode(dice);
        int[] expResult = {1, 2, 3, 5};
        int[] result = instance.getValue();
        assertArrayEquals(expResult, result);
>>>>>>> 9021f29edc306246c3bcb08021f6d1d17ede5df0
    }

    /**
     * Test of getChild method, of class TreeNode.
     */
    @Test
    public void testGetChild() {
        System.out.println("getChild");
<<<<<<< HEAD
        TreeNode instance = null;
        TreeNode expResult = null;
        TreeNode result = instance.getChild();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
=======
        int[] dice = {1, 2, 4, 5};
        TreeNode instance = new TreeNode(dice);
        int[] expResult = {1, 2, 4, 5, 1};
        int[] result = instance.getChild().getValue();
        assert(Arrays.equals(expResult, result));
>>>>>>> 9021f29edc306246c3bcb08021f6d1d17ede5df0
    }

    /**
     * Test of getSibling method, of class TreeNode.
     */
    @Test
    public void testGetSibling() {
        System.out.println("getSibling");
<<<<<<< HEAD
        TreeNode instance = null;
        TreeNode expResult = null;
        TreeNode result = instance.getSibling();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
=======
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
>>>>>>> 9021f29edc306246c3bcb08021f6d1d17ede5df0
    }
    
}
