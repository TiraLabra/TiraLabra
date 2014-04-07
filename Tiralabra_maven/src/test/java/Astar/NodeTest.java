package Astar;

import junit.framework.TestCase;

/**
 *
 * @author Arto
 */
public class NodeTest extends TestCase {
    Node n = new Node('*',0,0);
    
    
    /**
     * Test creation of a node
     */
    public void testNodeCreation()
    {
        assertTrue(n.getValue()=='*');
    }
    
    public void testValueModification() {
        n.setValue('H');
        assertTrue(n.getValue()=='H');
    }
    
    public void testToStartModification() {
        n.setToStart(100);
        assertTrue(n.getToStart()==100);
    }
    
    public void testPriority() {
        n.setToStart(1);
        n.setToGoal(4, 4);
        assertTrue(n.getPrio()==Math.sqrt(32)+1);
    }
}
