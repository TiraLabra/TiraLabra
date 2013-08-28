
package com.mycompany.tiralabra_maven.logiikka;

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
public class RegexKasittelijaTest {
    
    private Regexkasittelija rk;
    
    public RegexKasittelijaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        rk = new Regexkasittelija();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAsetaSaannollinenLauseke() {
        rk.asetaSaannollinenLauseke(new Jono<>("a", "b", "|", "c", ".", "\\?", "."));
    }

    @Test
    public void testTasmaa() {
        assertTrue(rk.tasmaa("ac?"));
    }
}