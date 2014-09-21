/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Nimetön;

import Algoritmi.Verkko;
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
public class VerkkoTest {
    
    private Verkko testi;
    
    public VerkkoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testi=new Verkko(5, 4);
    }
    
    @After
    public void tearDown() {
        
        
    }

    
    @Test
    public void testKonstruktori(){
        
        assertEquals(testi.taulukko.length, 5);
        assertEquals(testi.taulukko[0].length, 4);
    }
    
    /**
     * Test of Täytätaulukko method, of class Verkko.
     */
    @Test
    public void testTäytätaulukko() {

        assertTrue(testi.taulukko[0][3].seinä);
        assertFalse(testi.taulukko[0][3].seinä);

    }

    /**
     * Test of HeuristiikkaArvo method, of class Verkko.
     */
    @Test
    public void testHeuristiikkaArvo() {
        System.out.println("HeuristiikkaArvo");
        int y = 0;
        int x = 0;
        Verkko instance = null;
        int expResult = 0;
        int result = instance.HeuristiikkaArvo(y, x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
