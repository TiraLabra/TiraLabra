
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
    }
    
    @After
    public void tearDown() {
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testEpakelpoLauseke() {
        a = new Automaatti(null);
    }
    
    @Test
    public void testKieliSisaltaa1() {
        lauseke = new Jono("a", ".");
        a = new Automaatti(lauseke);
        
        assertFalse(a.kieliSisaltaa("b"));
        assertTrue(a.kieliSisaltaa("a"));        
    }

    @Test
    public void testKieliSisaltaa2() {
        lauseke = new Jono("ab", ".");
        a = new Automaatti(lauseke);
        
        assertFalse(a.kieliSisaltaa("a"));
        assertFalse(a.kieliSisaltaa("b"));
        assertTrue(a.kieliSisaltaa("ab"));
    }
    
    @Test
    public void testKieliSisaltaa3() {
        lauseke = new Jono("a", ".", "b", ".", "c", ".");
        a = new Automaatti(lauseke);
        
        assertFalse(a.kieliSisaltaa("a"));
        assertFalse(a.kieliSisaltaa("b"));
        assertFalse(a.kieliSisaltaa("ab"));
        assertTrue(a.kieliSisaltaa("abc"));
    }
    
    @Test
    public void testKieliSisaltaa4() {
        lauseke = new Jono("a", "b", "c", ".");
        a = new Automaatti(lauseke);
        
        assertFalse(a.kieliSisaltaa("a"));
        assertFalse(a.kieliSisaltaa("b"));
        assertFalse(a.kieliSisaltaa("ab"));
        assertTrue(a.kieliSisaltaa("abc"));
    }
    
    @Test
    public void testKieliSisaltaa5() {
        lauseke = new Jono("aaa", ".");
        a = new Automaatti(lauseke);
        
        assertFalse(a.kieliSisaltaa("a"));
        assertFalse(a.kieliSisaltaa("aa"));
        assertTrue(a.kieliSisaltaa("aaa"));
    }
    
    @Test
    public void testKieliSisaltaa6() {
        lauseke = new Jono("a", "?", "b", "?", "c", "?");
        a = new Automaatti(lauseke);
        
        assertFalse(a.kieliSisaltaa("d"));
        assertFalse(a.kieliSisaltaa("aa"));
        assertTrue(a.kieliSisaltaa("ac"));
    }
    
    @Test
    public void testKieliSisaltaa7() {
        lauseke = new Jono("a", "b", "c", "|");
        a = new Automaatti(lauseke);
        System.out.println(a);
        
        assertFalse(a.kieliSisaltaa("d"));
        assertFalse(a.kieliSisaltaa("aa"));
        assertFalse(a.kieliSisaltaa("ab"));
        assertFalse(a.kieliSisaltaa("aaa"));
        assertTrue(a.kieliSisaltaa("a"));
    }
    
    @Test
    public void testKieliSisaltaa8() {
        lauseke = new Jono("a", ".", "a", "*");
        a = new Automaatti(lauseke);
        
        assertFalse(a.kieliSisaltaa("d"));
        assertTrue(a.kieliSisaltaa("a"));
        assertTrue(a.kieliSisaltaa("aa"));
        assertTrue(a.kieliSisaltaa("aaa"));
    }
    
    @Test
    public void testKieliSisaltaa9() {
        lauseke = new Jono("a", ".", "a", "+");
        a = new Automaatti(lauseke);
        
        assertFalse(a.kieliSisaltaa("d"));
        assertFalse(a.kieliSisaltaa("a"));
        assertTrue(a.kieliSisaltaa("aa"));
        assertTrue(a.kieliSisaltaa("aaa"));
    }
    
    @Test
    public void testKieliSisaltaa10() {
        lauseke = new Jono("b", ".", "a", "+", "cd", "e", "f", "|", "d", "|", "g", "?");
        Tila.nollaaTilalaskuri();
        a = new Automaatti(lauseke);
        System.out.println(a);
        
        assertFalse(a.kieliSisaltaa("d"));
        assertFalse(a.kieliSisaltaa("ba"));
        assertFalse(a.kieliSisaltaa("baaeg"));
        assertTrue(a.kieliSisaltaa("bacddg"));
        assertTrue(a.kieliSisaltaa("baaaaaaed"));
    }
    
    @Ignore // Pitänee tehdä tästä issue Githubiin...
    @Test
    public void testKieliSisaltaa11() {
        lauseke = new Jono("ab", "bc", "cd", "|", "*");
        Tila.nollaaTilalaskuri();
        a = new Automaatti(lauseke);
        System.out.println(a);
        
        assertFalse(a.kieliSisaltaa("d"));
        assertFalse(a.kieliSisaltaa("ba"));
        assertFalse(a.kieliSisaltaa("ab"));
        assertTrue(a.kieliSisaltaa("ababab"));
        assertTrue(a.kieliSisaltaa("abcdabbcbc"));
    }
}