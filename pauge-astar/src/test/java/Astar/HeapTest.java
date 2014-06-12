package Astar;

import junit.framework.TestCase;

/**
 *
 * @author Arto
 */
public class HeapTest extends TestCase {
        Heap h = new Heap();
        Node n1 = new Node('*',0,0);
        Node n2 = new Node('*',1,1);
    /**
     * Test creation of a Heap
     */
    public void testHeapCreation() {
        assertTrue(h!=null);
    }
    
    /**
     * Test if the correct node is returned from heap
     */
    public void testGetHighest() {
        n1.setToStart(1);
        n1.setToGoal(10, 10);
        n2.setToStart(0);
        n2.setToGoal(10, 10);
        h.insertNode(n1);
        h.insertNode(n2);
        assertTrue(h.getHighest()==n2);
    }
    
    /*public void testRemoveNode() {
        Node n3 = new Node('*', 2,2);
        n1.setToStart(1);
        n1.setToGoal(10, 2);
        n2.setToStart(0);
        n2.setToGoal(10, 2);
        n3.setToStart(3);
        n3.setToGoal(10, 2);
        
        h.insertNode(n1);
        h.insertNode(n2);
        h.insertNode(n3);
        
        assertTrue(h.getHighest()==n3);
        h.removeNode();
        assertTrue(h.getHighest()==n2);
        
    }*/
}
