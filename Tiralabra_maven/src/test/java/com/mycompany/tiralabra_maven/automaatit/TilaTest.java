
package com.mycompany.tiralabra_maven.automaatit;

import com.mycompany.tiralabra_maven.tietorakenteet.AvainArvoJonoTest;
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
        Jono<Tila> oikeavastaus = new Jono<>();
        oikeavastaus.lisaa(u);
        AvainArvoJonoTest.vertaaJonoja(oikeavastaus, t.tilasiirtymat('a'));
        oikeavastaus.tyhjenna();
        oikeavastaus.lisaa(t);
        AvainArvoJonoTest.vertaaJonoja(oikeavastaus, t.tilasiirtymat('b'));
        assertNull(t.tilasiirtymat('c'));
        assertFalse(t.ON_HYVAKSYVA);
        t = t.tilasiirtymat('a').poista();
        assertTrue(t.ON_HYVAKSYVA);
    }

}