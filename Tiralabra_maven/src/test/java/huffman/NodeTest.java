/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffman;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author alpa
 */
public class NodeTest extends TestCase {
    
    /**
     *
     * @param testName
     */
    public NodeTest(String testName) {
        super(testName);
    }

    /**
     *
     * @return
     */
    public static Test suite() {
        TestSuite suite = new TestSuite(NodeTest.class);
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
     * Test of add method, of class Node.
     */
    public void testAdd() {
        Node instance = null;
        instance.add();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChara method, of class Node.
     */
    public void testGetChara() {
        Node instance = null;
        int expResult = 0;
        int result = instance.getChara();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFreq method, of class Node.
     */
    public void testGetFreq() {
        Node instance = null;
        int expResult = 0;
        int result = instance.getFreq();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRChild method, of class Node.
     */
    public void testSetRChild() {
        Node instance = null;
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLChild method, of class Node.
     */
    public void testSetLChild() {
        Node instance = null;
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRChild method, of class Node.
     */
    public void testGetRChild() {
        Node instance = null;
        Node expResult = null;
        Node result = instance.getRChild();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLChild method, of class Node.
     */
    public void testGetLChild() {
        Node instance = null;
        Node expResult = null;
        Node result = instance.getLChild();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
