
package com.mycompany.tiralabra_maven.totuusfunktiot;

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
public class IdentiteettiTest {
    
    public IdentiteettiTest() {
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
    public void testTayttaa() {
        Totuusfunktio R = new Identiteetti();
        String a = "a";
        String b = "b";
        String c = "a";
        assertTrue(R.tayttaa(a, a));
        assertFalse(R.tayttaa(a, b));
        assertTrue(R.tayttaa(a, c));
    }
}