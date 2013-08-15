
package com.mycompany.tiralabra_maven.automaatit;

import com.mycompany.tiralabra_maven.automaatit.Automaattisolmu;
import com.mycompany.tiralabra_maven.totuusfunktiot.Identiteetti;
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
public class AutomaattisolmuTest {
    
    private Automaattisolmu a;
    
    public AutomaattisolmuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        a = new Automaattisolmu(1, new Identiteetti(), "a");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testTilasiirtyma() {
        Automaattisolmu b = new Automaattisolmu(2, null, "b");
        a.asetaSeuraaja(b);
        assertEquals(b, a.tilasiirtyma("a"));
        assertNull(a.tilasiirtyma('c'));
    }
}