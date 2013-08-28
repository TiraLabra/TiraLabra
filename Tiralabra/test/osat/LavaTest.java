package osat;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author albis
 */
public class LavaTest {
    private Nelikulmio lava;
    
    public LavaTest() {
    }
    
    @Before
    public void setUp() {
        lava = new Nelikulmio(80, 120, 130);
    }
    
    @Test
    public void leveysAsettuuOikein() {
        assertEquals(80, lava.getLeveys());
    }
    
    @Test
    public void pituusAsettuuOikein() {
        assertEquals(120, lava.getPituus());
    }
    
    @Test
    public void korkeusAsettuuOikein() {
        assertEquals(130, lava.getKorkeus());
    }
}
