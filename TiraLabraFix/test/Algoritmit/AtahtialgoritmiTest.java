/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algoritmit;

import Tietorakenteet.Abstraktisolmu;
import Tietorakenteet.Verkko;
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
public class AtahtialgoritmiTest {
    
    /**
     *
     */
    private Atahtialgoritmi algoritmi;
    
  
    
 
    @Before
    public void setUp() {
        
      
    }
    
    
    /**
     * Test of palautaVerkko method, of class Atahtialgoritmi.
     */
 
    @Test
    public void testAsetaPisteet() {
        System.out.println("asetaPisteet");
        Abstraktisolmu alku = null;
        Abstraktisolmu loppu = null;
        Atahtialgoritmi instance = null;
        instance.asetaPisteet(alku, loppu);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of laske method, of class Atahtialgoritmi.
     */
    @Test
    public void testLaske() {
        System.out.println("laske");
        Atahtialgoritmi instance = null;
        boolean expResult = false;
        boolean result = instance.laske();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initKeko method, of class Atahtialgoritmi.
     */
    @Test
    public void testInitKeko() {
        System.out.println("initKeko");
        Atahtialgoritmi instance = null;
        instance.initKeko();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rakennapolku method, of class Atahtialgoritmi.
     */
    @Test
    public void testRakennapolku() {
        System.out.println("rakennapolku");
        Atahtialgoritmi instance = null;
        instance.rakennapolku();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
