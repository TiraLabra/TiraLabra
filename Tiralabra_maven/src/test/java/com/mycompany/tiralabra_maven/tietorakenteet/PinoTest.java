
package com.mycompany.tiralabra_maven.tietorakenteet;

import java.util.EmptyStackException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PinoTest {
    
    public PinoTest() {
    }
    
    private Pino<Object>    pino;
    private Object          testiObjekti;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pino            = new Pino<Object>();
        testiObjekti    = new Object();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testEmpty() {
        pino.lisaa(testiObjekti);
        assertFalse(pino.onTyhja());
    }

    @Test
    public void testPush() {
        pino.lisaa(testiObjekti);
        assertEquals(pino.koko(), 1);
    }

    @Test
    public void testPeek() {
        pino.lisaa(testiObjekti);
        assertEquals(pino.kurkista(), testiObjekti);
    }
    
    @Test(expected = EmptyStackException.class)
    public void testPeek2() {
        pino.kurkista();
    }

    @Test
    public void testPop() {
        pino.lisaa(testiObjekti);
        assertEquals(pino.poista(), testiObjekti);
    }

    @Test
    public void testSize() {
        pino.lisaa(testiObjekti);
        pino.lisaa(testiObjekti);
        pino.lisaa(testiObjekti);
        pino.lisaa(testiObjekti);
        assertEquals(pino.koko(), 4);
    }
}