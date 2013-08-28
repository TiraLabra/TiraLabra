
package com.mycompany.tiralabra_maven.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
@Ignore // Ei ole nyt aikaa tehdä tätä testiä.
public class RegextulkkiTest {
    
    public RegextulkkiTest() {
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
     * Test of merkkiOnLyhenne method, of class Regextulkki.
     */
    @Test
    public void testMerkkiOnLyhenne() {
        System.out.println("merkkiOnLyhenne");
        Regextulkki instance = new Regextulkki();
        boolean expResult = false;
        boolean result = instance.merkkiOnLyhenne();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of merkkiOnOperaattori method, of class Regextulkki.
     */
    @Test
    public void testMerkkiOnOperaattori() {
        System.out.println("merkkiOnOperaattori");
        Regextulkki instance = new Regextulkki();
        boolean expResult = false;
        boolean result = instance.merkkiOnOperaattori();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of merkkiOnDataa method, of class Regextulkki.
     */
    @Test
    public void testMerkkiOnDataa() {
        System.out.println("merkkiOnDataa");
        Regextulkki instance = new Regextulkki();
        boolean expResult = false;
        boolean result = instance.merkkiOnDataa();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of kasitteleLyhenne method, of class Regextulkki.
     */
    @Test
    public void testKasitteleLyhenne() {
        System.out.println("kasitteleLyhenne");
        Regextulkki instance = new Regextulkki();
        instance.kasitteleLyhenne();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of kasitteleData method, of class Regextulkki.
     */
    @Test
    public void testKasitteleData() {
        System.out.println("kasitteleData");
        Regextulkki instance = new Regextulkki();
        instance.kasitteleData();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}