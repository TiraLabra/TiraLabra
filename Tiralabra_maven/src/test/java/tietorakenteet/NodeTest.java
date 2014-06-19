
package tietorakenteet;

import junit.framework.Test;
import junit.framework.TestCase;



/**
 *
 * @author jsopakar
 */
public class NodeTest extends TestCase {
    
    public NodeTest(String testName) {
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
    
    //@Test
    public void testToimiikoLuonti() {
        Node n = new Node(1,2,3);
        
        assertEquals(1, n.getRivi());
        assertEquals(2, n.getSarake());
        assertEquals(3, n.getKustannus());
    }
    
    public void testToimiikoVertaus() {
        Node n1 = new Node(1,1,0);
        Node n2 = new Node(3,3,0);
        n1.setEtaisyysAlusta(1);
        n2.setEtaisyysAlusta(3);
        
        int vertailu = n1.compareTo(n2);
        boolean onkoNeg = vertailu < 0;
        assertEquals(true, onkoNeg);
    }
    
    public void testToimiikoToString() {
        Node n = new Node(2,3,4);
        String tulos = n.toString();
        
        assertEquals("2, 3 G: " + Integer.MAX_VALUE + " H: 0", tulos);
    }
    
    public void testToimiikoSeinatestaus() {
        Node n1 = new Node(1,2,5);
        Node n2 = new Node(8,7,6);
        
        assertEquals(true, n1.kuljettavissa());
        assertEquals(false, n2.kuljettavissa());
    }
}
