/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TiralabraTest;

import java.util.PriorityQueue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.tietorakenteet.Node;
import tiralabra.tietorakenteet.Puu;

/**
 *
 * @author Joonas
 */
public class PuuTest {
    
    private Puu puu;
    
    public PuuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     *           -1,100
     *         /         \ 
     *      97,45       -1,55 
     *             /            \
     *          -1,25           -1,30
     *         /     \         /     \
     *       99,12   98,13   -1,14  100,16
     *                      /     \
     *                   102,5    101,9
     */

    @Before
    public void setUp() {
        PriorityQueue<Node> jono = new PriorityQueue<Node>();
        jono.add(new Node(97, 45));
        jono.add(new Node(98, 13));
        jono.add(new Node(99, 12));
        jono.add(new Node(100, 16));
        jono.add(new Node(102, 5));
        jono.add(new Node(101, 9));
        
        this.puu = new Puu(jono);
    }
    

    @After
    public void tearDown() {
    }
    
    @Test
    public void kokoaaOikein(){
        puu.kokoa();
        assertEquals(-1, puu.getRoot().getMerkki());
        assertEquals(45+13+12+16+5+9, puu.getRoot().getToistot());
    }
    
    @Test
    public void kokoaaOikein2(){
        puu.kokoa();
        assertEquals(97, puu.getRoot().getVasen().getMerkki());
        assertEquals(45, puu.getRoot().getVasen().getToistot());
        
        assertEquals(-1, puu.getRoot().getOikea().getMerkki());
        assertEquals(55, puu.getRoot().getOikea().getToistot());
        
    }
    
    @Test
    public void kokoaaOikein3(){
        puu.kokoa();
        assertEquals(-1, puu.getRoot().getOikea().getOikea().getMerkki());
        assertEquals(30, puu.getRoot().getOikea().getOikea().getToistot());
        
        assertEquals(100, puu.getRoot().getOikea().getOikea().getOikea().getMerkki());
        assertEquals(16, puu.getRoot().getOikea().getOikea().getOikea().getToistot());
        
        assertEquals(102, puu.getRoot().getOikea().getOikea().getVasen().getVasen().getMerkki());
        assertEquals(5, puu.getRoot().getOikea().getOikea().getVasen().getVasen().getToistot());
    }
    
    
    @Test
    public void muodostaaReitit(){
        puu.kokoa();
        puu.muodostaReitit(puu.getRoot(), "");
        String[] reitit = puu.getReitit();
        assertEquals("0",reitit[97]);
        assertEquals("101",reitit[98]);
        assertEquals("100",reitit[99]);
        assertEquals("111",reitit[100]);
        assertEquals("1101",reitit[101]);
        assertEquals("1100",reitit[102]);
    }
    
    
}
