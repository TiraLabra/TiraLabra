/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TiralabraTest.tietorakenteet;

import java.util.PriorityQueue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.tietorakenteet.Node;
import tiralabra.tietorakenteet.PrioriteettiJono;
import tiralabra.tietorakenteet.Puu;

/**
 *
 * @author Joonas
 */
public class PuuTest {
    
    private Puu puu;
    private Puu reititPuu;
    
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
        PrioriteettiJono jono = new PrioriteettiJono();
        jono.lisaa(new Node(97, 45));
        jono.lisaa(new Node(98, 13));
        jono.lisaa(new Node(99, 12));
        jono.lisaa(new Node(100, 16));
        jono.lisaa(new Node(102, 5));
        jono.lisaa(new Node(101, 9));
        
        this.puu = new Puu(jono);
        
        String[] polut = new String[256];
        polut[97] = "0";
        polut[98] = "1";
        polut[99] = "00";
        this.reititPuu = new Puu(polut);
    }
    

    @After
    public void tearDown() {
    }
    
    
    @Test
    public void juuriOikein(){
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
    
    @Test
    public void lisaaANodenReiteilla(){
        reititPuu.lisaaNode(97, "0", reititPuu.getRoot());
        assertEquals(97, reititPuu.getRoot().getVasen().getMerkki());
    }
    
    @Test
    public void lisaaBNodenReiteilla(){
        reititPuu.lisaaNode(98, "01", reititPuu.getRoot());
        assertEquals(98, reititPuu.getRoot().getVasen().getOikea().getMerkki());
    }
    
    @Test
    public void lisaaNodenPitkallaReitilla(){
        reititPuu.lisaaNode(97, "00001101", reititPuu.getRoot());
        assertEquals(97, reititPuu.getRoot().getVasen().getVasen().getVasen().getVasen().getOikea().getOikea().getVasen().getOikea().getMerkki());
    }
    
    @Test
    public void matkallaOlevatNodetOikein(){
        reititPuu.lisaaNode(97, "00001101", reititPuu.getRoot());
        assertEquals(-1, reititPuu.getRoot().getVasen().getMerkki());
    }
    
    @Test
    public void eiYlimaaraisiaNodeja(){
        reititPuu.lisaaNode(97, "00001101", reititPuu.getRoot());
        assertEquals(null, reititPuu.getRoot().getOikea());
    }
    @Test
    public void eiYlimaaraisiaNodeja2(){
        reititPuu.lisaaNode(97, "00001101", reititPuu.getRoot());
        assertEquals(null, reititPuu.getRoot().getVasen().getVasen().getOikea());
    }
    
    @Test
    public void kokoaaReiteista(){
        reititPuu.kokoaReiteista();
        assertEquals(-1, reititPuu.getRoot().getMerkki());
    }
    
    @Test
    public void kokoaaReiteistaAOikeallaPaikalla(){
        reititPuu.kokoaReiteista();
        assertEquals(97, reititPuu.getRoot().getVasen().getMerkki());
    }
    
    @Test
    public void kokoaaReiteistaBOikeallaPaikalla(){
        reititPuu.kokoaReiteista();
        assertEquals(98, reititPuu.getRoot().getOikea().getMerkki());
    }
    
    @Test
    public void kokoaaReiteistaCOikeallaPaikalla(){
        reititPuu.kokoaReiteista();
        assertEquals(99, reititPuu.getRoot().getVasen().getVasen().getMerkki());
    }
    
    
}
