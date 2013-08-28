
package com.mycompany.tiralabra_maven.suorituskykytestit;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public class SuorituskykytyokalutTest {
    
    public SuorituskykytyokalutTest() {
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
     * Test of keskiarvo method, of class Suorituskykytyokalut.
     */
    @Test
    public void testKeskiarvo() {
        long[] luvut = { 1, 2, 3 };
        assertEquals(2.0, Suorituskykytyokalut.keskiarvo(luvut), 0.01);
    }

    /**
     * Test of pienin method, of class Suorituskykytyokalut.
     */
    @Test
    public void testPienin() {
        long[] luvut = { 1, 2, 3 };
        assertEquals(1, Suorituskykytyokalut.pienin(luvut));
    }

    /**
     * Test of suurin method, of class Suorituskykytyokalut.
     */
    @Test
    public void testSuurin() {
        long[] luvut = { 1, 2, 3 };
        assertEquals(3, Suorituskykytyokalut.suurin(luvut));
    }
}