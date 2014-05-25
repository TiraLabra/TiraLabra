/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffman;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.PriorityQueue;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author alpa
 */
public class TreeBuilderTest extends TestCase {
    
    /**
     *
     * @param testName
     */
    public TreeBuilderTest(String testName) {
        super(testName);
    }

    /**
     *
     * @return
     */
    public static Test suite() {
        TestSuite suite = new TestSuite(TreeBuilderTest.class);
        return suite;
    }
    
    /**
     *
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    /**
     *
     * @throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of countFrequencies method, of class TreeBuilder.
     * @throws java.lang.Exception
     */
    public void testCountFrequencies() throws Exception {
        FileInputStream file = null;
        TreeBuilder instance = null;
        ArrayList<Node> expResult = null;
        ArrayList<Node> result = instance.countFrequencies();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createQueue method, of class TreeBuilder.
     */
    public void testCreateQueue() {
        ArrayList<Node> freqs = null;
        TreeBuilder instance = null;
        PriorityQueue<Node> expResult = null;
        PriorityQueue<Node> result = instance.createQueue(freqs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildTree method, of class TreeBuilder.
     * @throws java.lang.Exception
     */
    public void testBuildTree() throws Exception {
        TreeBuilder instance = null;
        Node expResult = null;
        Node result = instance.buildTree();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
