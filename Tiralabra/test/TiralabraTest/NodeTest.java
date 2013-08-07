package TiralabraTest;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tiralabra.Node;
import static org.junit.Assert.*;

/**
 *
 * @author Joonas
 */
public class NodeTest {
    
    private Node a;
    private Node c;
    
    public NodeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.a = new Node(97, 1);
        this.c = new Node(99, 3);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void palauttaaToistot() {
        assertEquals(1,a.getToistot());
        assertEquals(3,c.getToistot());
    }
    
    @Test
    public void getVasenLapsi() {
        assertEquals(null,a.getVasen());
    }
    
    @Test
    public void asetaVasenLapsi() {
        a.setVasen(c);
        assertEquals(c, a.getVasen());
    }
    @Test
    public void getOikeaLapsi() {
        assertEquals(null,a.getOikea());
    }
    
    @Test
    public void asetaOikeaLapsi() {
        a.setOikea(c);
        assertEquals(c, a.getOikea());
    }
    
    @Test
    public void getMerkki() {
        assertEquals(97, a.getMerkki());
        assertEquals(99, c.getMerkki());
    }
    
    @Test
    public void setMerkki() {
        a.setMerkki(101);
        c.setMerkki(100);
        assertEquals(101, a.getMerkki());
        assertEquals(100, c.getMerkki());
    }
   
    @Test
    public void vertaaNodeja(){ 
        assertEquals(-2, a.compareTo(c));
        assertEquals(2, c.compareTo(a));
    }
    
    
}
