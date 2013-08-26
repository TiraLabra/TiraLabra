
package com.mycompany.tiralabra_maven.tietorakenteet;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author johnny
 */
public class JonoTest {
    
    private Jono    jono;
    private Object  testiObjekti;
    
    /**
     *
     */
    public JonoTest() {
    }
    
    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     *
     */
    @Before
    public void setUp() {
        jono            = new Jono<>();
        testiObjekti    = new Object();
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     *
     */
    @Test
    public void testLisaa() {
        jono.lisaa(testiObjekti);
        jono.lisaa(null);
        jono.lisaa(null);
        jono.lisaa(new Object());
        jono.lisaa(new Object());
        assertEquals(testiObjekti, jono.poista());
        assertNull(jono.poista());
        assertNull(jono.poista());
        assertNotNull(jono.poista());
        assertNotNull(jono.poista());
    }
    
    /**
     *
     */
    @Test
    public void testKurkista() {
        assertNull(jono.kurkista());
        jono.lisaa(testiObjekti);
        assertEquals(testiObjekti, jono.kurkista());
        assertEquals(1, jono.pituus());
    }
    
    /**
     *
     */
    @Test
    public void testPituus() {
        jono.lisaa(testiObjekti);
        jono.lisaa(new Object());
        jono.lisaa(new Object());
        jono.lisaa(null);
        jono.lisaa(null);
        jono.lisaa(new Object());
        jono.lisaa(null);
        jono.lisaa(new Object());
        jono.lisaa(null);
        jono.lisaa(new Object());
        jono.lisaa(testiObjekti);
        jono.lisaa(new Object());
        assertEquals(12, jono.pituus());
    }

    /**
     *
     */
    @Test
    public void testPoista() {
        assertNull(jono.poista());
        jono.lisaa(testiObjekti);
        Object paluuarvo = jono.poista();
        assertEquals(testiObjekti, paluuarvo);
    }

    /**
     *
     */
    @Test
    public void testOnTyhja() {
        jono.lisaa(testiObjekti);
        assertFalse(jono.onTyhja());
    }

    /**
     *
     */
    @Test
    public void testTyhjenna() {
        jono.lisaa(testiObjekti);
        jono.tyhjenna();
        assertTrue(jono.onTyhja());
    }
    
    @Test
    public void testYhdista() {
        Jono<Integer> a = new Jono<>(1, 2, 3);
        Jono<Integer> b = new Jono<>(4, 5, 6);
        a.yhdista(b);
        Jono<Integer> c = new Jono<>(1, 2, 3, 4, 5, 6);
        AvainArvoJonoTest.vertaaJonoja(c, a);
    }
    
    @Test
    public void testYhdista2() {
        Jono<Integer> a = new Jono<>(1, 2, 3);
        Jono<Integer> b = null;
        a.yhdista(b);
        Jono<Integer> c = new Jono<>(1, 2, 3);
        AvainArvoJonoTest.vertaaJonoja(c, a);
    }
    
    @Test
    public void testToString() {
        assertEquals(jono.toString(), "\u2205");
        jono.lisaa(3);
        assertEquals(jono.toString(), "{3}");
        jono.lisaa(2);
        jono.lisaa(5);
        jono.lisaa(8);
        assertEquals(jono.toString(), "(3,2,5,8)");
    }
    
    @Test
    public void tuloste() {
        jono = new Jono("1", "+", "2", "*", "3");
        assertEquals(jono.tuloste(), "1 + 2 * 3 ");
    }
}
