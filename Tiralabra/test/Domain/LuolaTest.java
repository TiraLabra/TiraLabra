/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Emleri
 */
public class LuolaTest {
    Luola l;
    Hirvio h;
    
    public LuolaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        l = new Luola(5,5);
        h = new Hirvio(new Koordinaatit(0, 0));
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void luolaTest() {
        assertEquals(l.getKorkeus(), 5);
        assertEquals(l.getLeveys(), 5);
        
        assertEquals(l.getLuola().length, 5);
        assertEquals(l.getLuola()[1].length, 5);
    }
    
    @Test
    public void lisaaObjektiOnnistuuTest() {
        assertTrue(l.lisaaObjekti(h));
        assertEquals(l.getLuola()[0][0], h);
    }
    
    @Test
    public void lisaaObjektiEpaonnistuuTest() {
        Hirvio h2 = new Hirvio(new Koordinaatit(0, 0));
        assertTrue(l.lisaaObjekti(h));
        assertTrue(!l.lisaaObjekti(h2));
        assertEquals(l.getLuola()[0][0], h);
    }
    
    @Test
    public void siirraObjektiaOnnistuuTest() {
        l.lisaaObjekti(h);
        assertTrue(l.siirraObjektia(new Koordinaatit(0, 0), new Koordinaatit(1,1)));
        assertTrue(l.getLuola()[0][0] == null);
        assertEquals(l.getLuola()[1][1], h);
    }
    
    @Test
    public void siirraObjektiaEpaonnistuuKoskaUlkonaAlueeltaTest() {
        l.lisaaObjekti(h);
        assertTrue(!l.siirraObjektia(new Koordinaatit(0, 0), new Koordinaatit(8,8)));
        assertEquals(l.getLuola()[0][0], h);
    }
    
    @Test
    public void siirraObjektiaEpaonnistuuKoskaEiObjektiaSiirrettavaksiTest() {
        l.lisaaObjekti(h);
        assertTrue(!l.siirraObjektia(new Koordinaatit(1,1), new Koordinaatit(0,0)));
        assertEquals(l.getLuola()[0][0], h);
    }
}
