/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package java.huffman;

import huffman.HuffmanTest;
import huffman.NodeTest;
import huffman.TreeBuilderTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author alpa
 */
public class HuffmanSuite extends TestCase {
    
    /**
     *
     * @param testName
     */
    public HuffmanSuite(String testName) {
        super(testName);
    }
    
    /**
     *
     * @return
     */
    public static Test suite() {
        TestSuite suite = new TestSuite("HuffmanSuite");
        suite.addTest(TreeBuilderTest.suite());
        suite.addTest(NodeTest.suite());
        suite.addTest(HuffmanTest.suite());
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
    
}
