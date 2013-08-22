
package com.mycompany.tiralabra_maven.automaatit;

import com.mycompany.tiralabra_maven.tietorakenteet.Jono;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
@Ignore
public class AutomaattiTest {
    
    public AutomaattiTest() {
    }
    
    private Jono<String> lauseke;
    private Automaatti a;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        lauseke = new Jono<>();
        lauseke.lisaa("a");
        lauseke.lisaa("|");
        lauseke.lisaa("aa");
        lauseke.lisaa("|");
        lauseke.lisaa("ab");
        lauseke.lisaa("|");
        lauseke.lisaa("c");
        lauseke.lisaa(".");
        a = new Automaatti(lauseke);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of kieliSisaltaa method, of class Automaatti.
     */
    @Test
    public void testKieliSisaltaa() {
//        assertFalse(a.kieliSisaltaa("a"));
//        assertTrue(a.kieliSisaltaa("aac"));
        assertTrue(a.kieliSisaltaa("a"));
//        assertTrue(a.kieliSisaltaa("ab"));
    }
}