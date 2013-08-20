
package com.mycompany.tiralabra_maven.automaatit;

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
public class TilaTest {
    
    private Tila t;
    
    public TilaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        t = new Tila(false);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testTilasiirtyma() {
        Tila u = new Tila(true);
        t.lisaaTilasiirtyma('a', u);
        t.lisaaTilasiirtyma('b', t);
        assertEquals(u, t.tilasiirtyma('a'));
        assertEquals(t, t.tilasiirtyma('b'));
        assertNull(t.tilasiirtyma('c'));
        assertFalse(t.ON_HYVAKSYVA);
        t = t.tilasiirtyma('a');
        assertTrue(t.ON_HYVAKSYVA);
    }

}