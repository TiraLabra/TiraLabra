
package tietorakenteet;

import junit.framework.TestCase;

/**
 *
 * @author jsopakar
 */
public class ArrayListOmaTest extends TestCase {
    
    public ArrayListOmaTest(String testName) {
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
    
    public void testLuonti() {
        ArrayListOma ao = new ArrayListOma();
        
        assertEquals(0, ao.koko());
        assertEquals(10, ao.taulukonSisainenKoko());
    }
    
    public void testToimiikoYhdenLisays() {
        ArrayListOma ao = new ArrayListOma();
        ao.lisaa(new Node(1,1,1));
        
        assertEquals(1, ao.koko());
        
    }
    
    public void testToimiikoTaulukonKoonKasvatus() {
        ArrayListOma ao = new ArrayListOma(1);
        assertEquals(1, ao.taulukonSisainenKoko());
        Node n = new Node(1,1,1);
        ao.lisaa(n);
        ao.lisaa(n);
        ao.lisaa(n);
        ao.lisaa(n);
        
        assertEquals(4, ao.taulukonSisainenKoko());
        

    }
    
    public void testToimiikoSisaltaako() {
        ArrayListOma ao = new ArrayListOma();
        Node n1 = new Node(1,1,1);
        Node n2 = new Node(2,2,2);
        Node n3 = new Node(3,3,3);
        ao.lisaa(n1);
        ao.lisaa(n2);
        ao.lisaa(n3);
        
        assertEquals(true, ao.sisaltaako(n1));
        assertEquals(true, ao.sisaltaako(n3));
        assertEquals(false, ao.sisaltaako(new Node(4,4,4)));
        
        
    }
    
}
