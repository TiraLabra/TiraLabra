
package com.mycompany.tiralabra_maven.logiikka;

import com.mycompany.tiralabra_maven.tietorakenteet.Jono;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public class RegexKasittelijaTest {
    
    private Regexkasittelija rk;
    
    @Before
    public void setUp() {
        rk = new Regexkasittelija();
    }
    
    @Test
    public void testAsetaSaannollinenLauseke() {
        rk.asetaSaannollinenLauseke(new Jono<>("a", "b", "|", "c", "\\?", "."));
    }

    @Test
    public void testTasmaa() {
        assertTrue(rk.tasmaa("ac?"));
    }
}