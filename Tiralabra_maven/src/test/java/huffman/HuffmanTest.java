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
public class HuffmanTest extends TestCase {
    
    /**
     *
     * @param testName
     */
    public HuffmanTest(String testName) {
        super(testName);
    }

    /**
     *
     * @return
     */
    public static Test suite() {
        TestSuite suite = new TestSuite(HuffmanTest.class);
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
     * Test of run method, of class Huffman.
     * @throws java.lang.Exception
     */
    public void testRun() throws Exception {
        String filename = "";
        Huffman instance = new Huffman();
        instance.run(filename);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
