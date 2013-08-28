/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.logiikka;

import com.mycompany.tiralabra_maven.tietorakenteet.Jono;
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
public class TulkkiTest {
    
    public TulkkiTest() {
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
     * Test of tulkitseMerkkijono method, of class Tulkki.
     */
    @Test
    public void testTulkitseMerkkijono() {
        System.out.println("tulkitseMerkkijono");
        String MERKKIJONO = "";
        Tulkki instance = new TulkkiImpl();
        Jono expResult = null;
        Jono result = instance.tulkitseMerkkijono(MERKKIJONO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iteroiMerkit method, of class Tulkki.
     */
    @Test
    public void testIteroiMerkit() {
        System.out.println("iteroiMerkit");
        Tulkki instance = new TulkkiImpl();
        instance.iteroiMerkit();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of merkkiOnLyhenne method, of class Tulkki.
     */
    @Test
    public void testMerkkiOnLyhenne() {
        System.out.println("merkkiOnLyhenne");
        Tulkki instance = new TulkkiImpl();
        boolean expResult = false;
        boolean result = instance.merkkiOnLyhenne();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of merkkiOnOperaattori method, of class Tulkki.
     */
    @Test
    public void testMerkkiOnOperaattori() {
        System.out.println("merkkiOnOperaattori");
        Tulkki instance = new TulkkiImpl();
        boolean expResult = false;
        boolean result = instance.merkkiOnOperaattori();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of merkkiOnDataa method, of class Tulkki.
     */
    @Test
    public void testMerkkiOnDataa() {
        System.out.println("merkkiOnDataa");
        Tulkki instance = new TulkkiImpl();
        boolean expResult = false;
        boolean result = instance.merkkiOnDataa();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of kasitteleLyhenne method, of class Tulkki.
     */
    @Test
    public void testKasitteleLyhenne() {
        System.out.println("kasitteleLyhenne");
        Tulkki instance = new TulkkiImpl();
        instance.kasitteleLyhenne();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of kasitteleOperaattori method, of class Tulkki.
     */
    @Test
    public void testKasitteleOperaattori() {
        System.out.println("kasitteleOperaattori");
        Tulkki instance = new TulkkiImpl();
        instance.kasitteleOperaattori();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of kasitteleData method, of class Tulkki.
     */
    @Test
    public void testKasitteleData() {
        System.out.println("kasitteleData");
        Tulkki instance = new TulkkiImpl();
        instance.kasitteleData();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tyhjennaPino method, of class Tulkki.
     */
    @Test
    public void testTyhjennaPino() {
        System.out.println("tyhjennaPino");
        Tulkki instance = new TulkkiImpl();
        instance.tyhjennaPino();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of kaadu method, of class Tulkki.
     */
    @Test
    public void testKaadu() {
        System.out.println("kaadu");
        String VIESTI = "";
        Tulkki instance = new TulkkiImpl();
        instance.kaadu(VIESTI);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class TulkkiImpl extends Tulkki {

        public boolean merkkiOnLyhenne() {
            return false;
        }

        public boolean merkkiOnOperaattori() {
            return false;
        }

        public boolean merkkiOnDataa() {
            return false;
        }

        public void kasitteleLyhenne() {
        }

        public void kasitteleData() {
        }
    }
}