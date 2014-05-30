
package heuristiikat;

import junit.framework.TestCase;
import tietorakenteet.Node;

/**
 *
 * @author jsopakar
 */
public class ManhattanTest extends TestCase {
    
    public ManhattanTest(String testName) {
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
    
    public void testPerustapaus() {
        Node n1 = new Node(2,2,0);
        Node n2 = new Node(8,10,1);
        
        Manhattan m = new Manhattan();
        int tulos = m.laskeArvio(n1, n2);
        
        assertEquals(14, tulos);
        
    }
    
    public void testSamaNode() {
        Node n1 = new Node(5,5,1);
        Manhattan m = new Manhattan();
        int tulos = m.laskeArvio(n1, n1);
        
        assertEquals(0, tulos);
        
    }
    
    public void testKaanteinenJarjestys() {
        Node n2 = new Node(3,3,0);
        Node n1 = new Node(7,9,1);
        
        Manhattan m = new Manhattan();
        int tulos = m.laskeArvio(n1, n2);
        
        assertEquals(10, tulos);
        
    }
}
