
package com.mycompany.tiralabra_maven;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author noora
 */
public class SiirtoTest {
    
    public SiirtoTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void onkoSiirtoHyppyJosEiOleHyppy() {
        Siirto siirto = new Siirto(0, 1, 1, 0);
        assertEquals(false, siirto.onkoSiirtoHyppy());
    }
    
    @Test
    public void onkoSiirtoHyppyJosOnHyppy() {
        Siirto siirto = new Siirto(0, 1, 2, 3);
        assertEquals(true, siirto.onkoSiirtoHyppy());
    }


    
}
