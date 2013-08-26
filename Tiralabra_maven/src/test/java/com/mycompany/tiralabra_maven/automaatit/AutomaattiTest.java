
package com.mycompany.tiralabra_maven.automaatit;

import com.mycompany.tiralabra_maven.tietorakenteet.Jono;
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
//        lauseke = new Jono<>();
//        lauseke.lisaa("a");
//        lauseke.lisaa("|");
//        lauseke.lisaa("aa");
//        lauseke.lisaa("|");
//        lauseke.lisaa("ab");
//        lauseke.lisaa("|");
//        lauseke.lisaa("c");
//        lauseke.lisaa(".");
//        a = new Automaatti(lauseke);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of kieliSisaltaa method, of class Automaatti.
     */
//    @Ignore
    @Test
    public void testKieliSisaltaa() {
//        assertFalse(a.kieliSisaltaa("a"));
//        assertTrue(a.kieliSisaltaa("aac"));
//        assertTrue(a.kieliSisaltaa("a"));
//        assertTrue(a.kieliSisaltaa("ab"));
//        lauseke = new Jono<>("a", "?", "b", "?", "c", "?");
//        lauseke = new Jono<>("a", "b", "c", "|");
//        lauseke = new Jono<>("a", "b", "c", "|", "d", "?");
//        lauseke.lisaa("d");
//        lauseke.lisaa("?");
//        lauseke = new Jono("a", "?", "b", "?", "c", "?", "d", ".");
        lauseke = new Jono("a", "?", "bc", "?", "csd", "?", "d", ".");
        a = new Automaatti(lauseke);
        System.out.println(a);
        
        assertTrue(a.kieliSisaltaa("a"));
    }
}