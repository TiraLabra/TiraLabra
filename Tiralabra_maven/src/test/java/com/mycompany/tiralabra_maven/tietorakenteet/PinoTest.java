
package com.mycompany.tiralabra_maven.tietorakenteet;

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
        pino.push(testiObjekti);
        assertFalse(pino.empty());
    }

    @Test
    public void testPush() {
        pino.push(testiObjekti);
        assertEquals(pino.size(), 1);
    }

    @Test
    public void testPeek() {
        pino.push(testiObjekti);
        assertEquals(pino.peek(), testiObjekti);
    }

    @Test
    public void testPop() {
        pino.push(testiObjekti);
        assertEquals(pino.pop(), testiObjekti);
    }

    @Test
    public void testSize() {
        pino.push(testiObjekti);
        pino.push(testiObjekti);
        pino.push(testiObjekti);
        pino.push(testiObjekti);
        assertEquals(pino.size(), 4);
    }
}