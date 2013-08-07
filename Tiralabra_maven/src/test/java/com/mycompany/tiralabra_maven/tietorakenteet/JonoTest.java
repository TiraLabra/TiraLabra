
package com.mycompany.tiralabra_maven.tietorakenteet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class JonoTest {
    
    private Jono    jono;
    private Object  testiObjekti;
    
    public JonoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        jono            = new Jono<Object>();
        testiObjekti    = new Object();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testLisaa() {
        assertTrue(jono.lisaa(testiObjekti));
    }
    
    @Test
    public void testKurkista() {
        assertNull(jono.kurkista());
        jono.lisaa(testiObjekti);
        assertEquals(testiObjekti, jono.kurkista());
        assertEquals(1, jono.pituus());
    }
    
    @Test
    public void testPituus() {
        jono.lisaa(testiObjekti);
        jono.lisaa(new Object());
        jono.lisaa(new Object());
        jono.lisaa(new Object());
        jono.lisaa(new Object());
        jono.lisaa(new Object());
        jono.lisaa(testiObjekti);
        jono.lisaa(new Object());
        assertEquals(8, jono.pituus());
    }

    @Test
    public void testPoista() {
        assertNull(jono.poista());
        jono.lisaa(testiObjekti);
        Object paluuarvo = jono.poista();
        assertEquals(testiObjekti, paluuarvo);
    }

    @Test
    public void testKoko() {
        jono.lisaa(testiObjekti);
        jono.lisaa(testiObjekti);
        assertEquals(2, jono.koko());
    }

    @Test
    public void testOnTyhja() {
        jono.lisaa(testiObjekti);
        assertFalse(jono.onTyhja());
    }

    @Test
    public void testTyhjenna() {
        jono.lisaa(testiObjekti);
        jono.tyhjenna();
        assertTrue(jono.onTyhja());
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
}
