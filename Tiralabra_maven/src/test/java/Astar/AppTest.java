package Astar;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Testing of the Astar app
 */
public class AppTest 
    extends TestCase
{
    
    public AppTest(){
    }

    /**
     * Test creation of a node
     */
    public void testNodeCreation()
    {
        Node n = new Node('*',0,0);
        assertTrue(n.getValue()=='*');
    }
    /**
     * Test creation of a map
     */
    public void testMapCreation() {
        Map m = new Map();
        assertTrue(m!=null);
    }
    
    /**
     * Test creation of a Finder
     */
    public void testFinderCreation(){
        Finder f = new Finder();
        assertTrue(f!=null);
    }
    
    /**
     * Test creation of a Heap
     */
    public void testHeapCreation() {
        Heap h = new Heap();
        assertTrue(h!=null);
    }
}
