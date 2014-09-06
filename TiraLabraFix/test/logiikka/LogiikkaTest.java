/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logiikka;

import Tietorakenteet.Jatkuvamonikulmio;
import Tietorakenteet.Jono.Jono;
import Tietorakenteet.Kordinaatti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Serafim
 */
public class LogiikkaTest {
    
    public LogiikkaTest() {
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
     * Test of sallitaanko method, of class Logiikka.
     */
    @Test
    public void testSallitaanko() {
        System.out.println("sallitaanko");
        Jatkuvamonikulmio d = null;
        Logiikka instance = new Logiikka();
        boolean expResult = false;
        boolean result = instance.sallitaanko(d);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leikkaako method, of class Logiikka.
     */
    @Test
    public void testLeikkaako() {
        System.out.println("leikkaako");
        Kordinaatti k1 = null;
        Kordinaatti k2 = null;
        Jatkuvamonikulmio a = null;
        Logiikka instance = new Logiikka();
        boolean expResult = false;
        boolean result = instance.leikkaako(k1, k2, a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
