
package com.mycompany.tiralabra_maven.tietorakenteet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Markus
 */
public class TestipuuTest {
   
    Testipuu puu;
   
    public TestipuuTest() {
    }
    
    
    @Before
    public void setUp() {
        puu = new Testipuu();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of lisaa method, of class Testipuu.
     */
    @Test
    public void testLisaa() {
        //miten tätä testaa järkevästi?
        puu.lisaa(0);
    }

    /**
     * Test of hae method, of class Testipuu.
     */
    @Test
    public void testHae() {
        int arvo = 0;
        boolean tulos = puu.hae(arvo);
        assertEquals(false, tulos);
        puu.lisaa(arvo);
        tulos = puu.hae(arvo);
        assertEquals(true, tulos);
    }

    /**
     * Test of poista method, of class Testipuu.
     */
    @Test
    public void testPoista() {
        int arvo = 0;
        puu.lisaa(arvo);
        boolean tulos = puu.hae(arvo);
        assertEquals(true, tulos);
        puu.poista(arvo);
        tulos = puu.hae(arvo);
        assertEquals(false, tulos);
    }

    /**
     * Test of lisaaKaikki method, of class Testipuu.
     */
    @Test
    public void testLisaaKaikki() {
        int[] arvot = {1,2}; 
        puu.lisaaKaikki(arvot);
        for (int i : arvot) {
            boolean tulos = puu.hae(i);
            assertEquals(true, tulos);
        }
    }
    
}
