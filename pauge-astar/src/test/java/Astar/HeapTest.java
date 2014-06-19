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
        n1.setToStart(0);
        n1.setToGoal(10, 10);
        n2.setToStart(1);
        n2.setToGoal(10, 10);
        h.insertNode(n1);
        h.insertNode(n2);
        assertTrue(h.getHighest()==n2);
    }
    
    /**
     * Test removal works and priority order is kept after removal.
     * Sorting of the heap is also tested as priority order is sustained.
     */
    public void testRemoveNode() {
        Node n3 = new Node('*', 2,2);
        n1.setToStart(0);
        n1.setToGoal(10, 2);
        n2.setToStart(1);
        n2.setToGoal(10, 2);
        n3.setToStart(3);
        n3.setToGoal(10, 2);
        
        h.insertNode(n1);
        h.insertNode(n2);
        h.insertNode(n3);
        
        double a = h.getHighest().getPrio();
        h.removeNode();
        double b = h.getHighest().getPrio();
        h.removeNode();
        assertTrue(a<b && b<h.getHighest().getPrio());
    }
    
    /**
     * A heap is created with a table with the size of 10. If after continuous additions 
     * the size is more than 10 the increase has been successful
     */
    public void testIncreaseSize() {
        Heap h1 = new Heap();
        int i = 1;
        while(i<=11) {
            h1.insertNode(new Node('*',i,i));
            i++;
        }
        assertTrue(h1.getSize()>10);
    }
    
    /**
     *  Test remaining getters, a test of their own is not needed
     */
    public void testRemainingGetters() {
        h.insertNode(n1);
        assertTrue(h.hasNode(n1));
        assertTrue(h.get(1).equals(n1));
        h.removeNode();
        assertTrue(h.isEmpty());
    }
}
