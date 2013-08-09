/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Astar.Ruutu;
import Astar.AtahtiReitinhaku;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tokaviikko.AtahtiReitinhaku;

/**
 *
 * @author Ile
 */
public class AtahtiReitinhakuTest {
    
    public AtahtiReitinhakuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setLahto method, of class AtahtiReitinhaku.
     */
    @Test
    public void testSetLahto() {
        System.out.println("setLahto");
        int lahtoY = 0;
        int lahtoX = 0;
        AtahtiReitinhaku instance = null;
        boolean expResult = false;
        boolean result = instance.setLahto(lahtoY, lahtoX);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMaali method, of class AtahtiReitinhaku.
     */
    @Test
    public void testSetMaali() {
        System.out.println("setMaali");
        int maaliY = 0;
        int maaliX = 0;
        AtahtiReitinhaku instance = null;
        boolean expResult = false;
        boolean result = instance.setMaali(maaliY, maaliX);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aTahtiAlgoritmi method, of class AtahtiReitinhaku.
     */
    @Test
    public void testATahtiAlgoritmi() {
        System.out.println("aTahtiAlgoritmi");
        int kork = 0;
        int lev = 0;
        AtahtiReitinhaku instance = null;
        char[][] expResult = null;
        char[][] result = instance.aTahtiAlgoritmi(kork, lev);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of lisaaKekoon method, of class AtahtiReitinhaku.
     */
    @Test
    public void testLisaaKekoon() {
        System.out.println("lisaaKekoon");
        int y = 0;
        int x = 0;
        int g = 0;
        Ruutu parent = null;
        AtahtiReitinhaku instance = null;
        instance.lisaaKekoon(y, x, g, parent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of poistaRuutuKeosta method, of class AtahtiReitinhaku.
     */
    @Test
    public void testPoistaRuutuKeosta() {
        System.out.println("poistaRuutuKeosta");
        AtahtiReitinhaku instance = null;
        instance.poistaRuutuKeosta();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
