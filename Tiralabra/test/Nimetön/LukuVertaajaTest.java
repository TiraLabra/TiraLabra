/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Nimetön;

import Algoritmi.Solmu;
import Algoritmi.LukuVertaaja;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jaakko
 */
public class LukuVertaajaTest {
    
    public LukuVertaajaTest() {
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
     * Test of compare method, of class LukuVertaaja.
     */
    @Test
    public void testCompare() {
        
        Solmu x = new Solmu(6, 6, 7);
        Solmu y = new Solmu(5, 6, 5);
        LukuVertaaja testi = new LukuVertaaja();
        int expResult = 1;
        int result = testi.compare(x, y);
        assertEquals(expResult, result);

    }
    
}
