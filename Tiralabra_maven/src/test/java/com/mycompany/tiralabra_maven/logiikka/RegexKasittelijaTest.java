
package com.mycompany.tiralabra_maven.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
@Ignore // Luokka ei ole vielä valmis (koska se on riippuvainen Automaatista).
public class RegexKasittelijaTest {
    
    private RegexKasittelija rk;
    
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
        rk = new RegexKasittelija();
    }
    
    @After
    public void tearDown() {
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAsetaSaannollinenLauseke() {
        rk.asetaSaannollinenLauseke(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testTasmaa() {
        rk.tasmaa(null);
    }
}