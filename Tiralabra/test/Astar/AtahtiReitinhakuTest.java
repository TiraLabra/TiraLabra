/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Astar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        int lahtoY = 1;
        int lahtoX = 1;
        AtahtiReitinhaku instance = null;
        boolean expResult = true;
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
     * Test of poistaKeosta method, of class AtahtiReitinhaku.
     */
    @Test
    public void testPoistaKeosta() {
        System.out.println("poistaKeosta");
        AtahtiReitinhaku instance = null;
        Ruutu expResult = null;
        Ruutu result = instance.poistaKeosta();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
