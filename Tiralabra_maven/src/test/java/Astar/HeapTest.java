package Astar;

import junit.framework.TestCase;

/**
 *
 * @author Arto
 */
public class HeapTest extends TestCase {
    
    /**
     * Test creation of a Heap
     */
    public void testHeapCreation() {
        Heap h = new Heap();
        assertTrue(h!=null);
    }
    
    /**
     * Test if the correct node is returned from heap
     */
    public void testGetHighest() {
        Node n1 = new Node('*',0,0);
        n1.setToStart(1);
        n1.setToGoal(10, 10);
        Node n2 = new Node('*',1,1);
        n2.setToStart(0);
        n2.setToGoal(10, 10);
        Heap h = new Heap();
        h.insertNode(n1);
        h.insertNode(n2);
        assertTrue(h.getHighest()==n2);
    }
}
