
package hakualgoritmit;

import heuristiikat.Manhattan;
import junit.framework.TestCase;
import tietorakenteet.Alue;
import tietorakenteet.Node;

/**
 *
 * @author jsopakar
 */
public class AStarTest extends TestCase {
    
    public AStarTest(String testName) {
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
    
    public void testKustannusarvio() {

        //16x16-testialue
        Alue alue = new Alue(16);
        alue.luoEsimerkkiTaulukko();
        
        AStar a = new AStar(new Manhattan());
        Node n1 = new Node(1, 1, 0);
        Node n2 = new Node(1, 2, 0);
        Node n3 = new Node(2, 1, 4);
        Node n4 = new Node(2, 2, 2);
        
        assertEquals(10, a.laskeKustannus(n1, n2));
        assertEquals(80, a.laskeKustannus(n1, n3));
        assertEquals(28, a.laskeKustannus(n1, n4));
    }
}
