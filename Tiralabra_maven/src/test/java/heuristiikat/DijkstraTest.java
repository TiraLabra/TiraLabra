
package heuristiikat;

import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import tietorakenteet.Node;

/**
 *
 * @author jsopakar
 */
public class DijkstraTest extends TestCase {
    
    public DijkstraTest(String testName) {
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
    
    public void testPalauttaakoNollan() {
        Node n1 = new Node(2,2,0);
        Node n2 = new Node(8,10,1);
        
        Dijkstra d = new Dijkstra();
        int tulos = d.laskeArvio(n1, n2);
        
        assertEquals(0, tulos);
    }
}
