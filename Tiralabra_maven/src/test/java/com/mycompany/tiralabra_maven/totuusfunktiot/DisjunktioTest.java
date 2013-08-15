
package com.mycompany.tiralabra_maven.totuusfunktiot;

import com.mycompany.tiralabra_maven.totuusfunktiot.Disjunktio;
import com.mycompany.tiralabra_maven.rajapinnat.Totuusfunktio;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public class DisjunktioTest {
    
    public DisjunktioTest() {
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

    @Test
    public void testTayttaa1() {
        Totuusfunktio V = new Disjunktio();
        assertFalse(V.tayttaa("3", "2", "5"));
    }
    
    @Test
    public void testTayttaa2() {
        Totuusfunktio V = new Disjunktio();
        assertTrue(V.tayttaa("3", "1", "2", "3"));
    }
}