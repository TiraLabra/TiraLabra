
package tietorakenteet;

import junit.framework.TestCase;

/**
 *
 * @author jsopakar
 */
public class NodeComparatorTest extends TestCase {
    
    public NodeComparatorTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}
    public void testPienempiSuurempi() {
        Node n1 = new Node(1, 1);
        Node n2 = new Node(2, 2);
        n1.setEtaisyysMaaliin(5);
        n2.setEtaisyysMaaliin(10);
        
        NodeComparator comparator = new NodeComparator();
        
        int vertailu = comparator.compare(n1, n2);
        
        assertEquals(-1, vertailu);
    }
    
    public void testSuurempiPienempi() {
        Node n1 = new Node(1, 1);
        Node n2 = new Node(2, 2);
        n1.setEtaisyysMaaliin(15);
        n2.setEtaisyysMaaliin(10);
        
        NodeComparator comparator = new NodeComparator();
        
        int vertailu = comparator.compare(n1, n2);
        
        assertEquals(1, vertailu);
    }
    
    public void testTasapeli() {
        Node n1 = new Node(1, 1);
        Node n2 = new Node(2, 2);
        n1.setEtaisyysMaaliin(10);
        n2.setEtaisyysMaaliin(10);
        
        NodeComparator comparator = new NodeComparator();
        
        int vertailu = comparator.compare(n1, n2);
        
        assertEquals(0, vertailu);
    }
    
}
