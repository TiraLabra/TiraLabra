
package heuristiikat;

import junit.framework.TestCase;
import tietorakenteet.Node;

/**
 *
 * @author jsopakar
 */
public class EuklidinenTest extends TestCase {
    
    public EuklidinenTest(String testName) {
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
    
    public void testRinnakkaissiirtyma() {
        
        Node n1 = new Node(1,1,1);
        Node n2 = new Node(1,2,1);
        Node n3 = new Node(5,1,1);
        Node n4 = new Node(1,8,5);
        
        Euklidinen e = new Euklidinen();
        
        assertEquals(10, e.laskeArvio(n1, n2));
        assertEquals(40, e.laskeArvio(n1, n3));
        assertEquals(70, e.laskeArvio(n4, n1));
    }
    
    public void testDiagonaalinenSiirtyma() {

        Node n1 = new Node(1,1,1);
        Node n2 = new Node(2,2,1);
        Node n3 = new Node(5,2,1);
        Node n4 = new Node(1,8,5);
        
        Euklidinen e = new Euklidinen();
        
        assertEquals(14, e.laskeArvio(n1, n2));
        assertEquals(41, e.laskeArvio(n1, n3));
        assertEquals(72, e.laskeArvio(n4, n3));
    }
    
    
}
