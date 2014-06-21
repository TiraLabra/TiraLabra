
package tietorakenteet;

import junit.framework.Test;
import junit.framework.TestCase;

/**
 *
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
        assertEquals(true, vertailu < 0);
    }
    
    public void testToimiikoSamanarvoistenVertailu() {
        Node n1 = new Node(1,2,0);
        Node n2 = new Node(2,1,0);
        n1.setEtaisyysAlusta(10);
        n1.setEtaisyysMaaliin(20);
        n2.setEtaisyysAlusta(20);
        n2.setEtaisyysMaaliin(10);
        
        int vertailu = n1.compareTo(n2);
        assertEquals(true, vertailu < 0);
    }
    
    public void testToimiikoToString() {
        Node n1 = new Node(2,3,4);
        Node n2 = new Node (1,2,3);
        n1.setEdellinen(n2);
        String tulos = n1.toString();
        assertEquals("2, 3 G: " + Integer.MAX_VALUE + " H: 0 edel: 1x2", tulos);
    }
    
    public void testToimiikoSeinatestaus() {
        Node n1 = new Node(1,2,5);
        Node n2 = new Node(8,7,6);
        
        assertEquals(true, n1.kuljettavissa());
        assertEquals(false, n2.kuljettavissa());
    }
    
    public void testGetteritSetterit() {
        Node n1 = new Node(1,2,3);
        Node n2 = new Node(2,3,4);
        
        n1.setEdellinen(n2);
        assertEquals(n2, n1.getEdellinen());
        
        n1.setEtaisyysAlusta(15);
        assertEquals(15, n1.getEtaisyysAlusta());
        
        n1.setOnReitilla(true);
        assertEquals(true, n1.onReitilla());
        
        n1.setLisattyNaapureihin(true);
        assertEquals(true, n1.onLisattyNaapureihin());
        
        n1.setKustannus(60);
        assertEquals(60, n1.getKustannus());
    }
}
