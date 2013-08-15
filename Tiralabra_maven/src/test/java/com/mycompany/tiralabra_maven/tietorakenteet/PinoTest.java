
package com.mycompany.tiralabra_maven.tietorakenteet;

import java.util.EmptyStackException;
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
public class PinoTest {
    
    /**
     *
     */
    public PinoTest() {
    }
    
    private Pino<Object>    pino;
    private Object          testiObjekti;
    
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
        pino            = new Pino<Object>();
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
    public void testOnTyhja() {
        pino.lisaa(testiObjekti);
        assertFalse(pino.onTyhja());
    }

    /**
     *
     */
    @Test
    public void testLisaa() {
        pino.lisaa(testiObjekti);
        assertEquals(pino.koko(), 1);
    }

    /**
     *
     */
    @Test
    public void testKurkista1() {
        pino.lisaa(testiObjekti);
        assertEquals(pino.kurkista(), testiObjekti);
    }
    
    /**
     *
     */
    @Test(expected = EmptyStackException.class)
    public void testKurkista2() {
        pino.kurkista();
    }

    /**
     *
     */
    @Test
    public void testPoista() {
        pino.lisaa(testiObjekti);
        assertEquals(pino.poista(), testiObjekti);
    }

    /**
     *
     */
    @Test
    public void testKoko() {
        pino.lisaa(testiObjekti);
        pino.lisaa(testiObjekti);
        pino.lisaa(testiObjekti);
        pino.lisaa(testiObjekti);
        assertEquals(pino.koko(), 4);
    }
}