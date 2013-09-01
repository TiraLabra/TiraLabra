package osat;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author albis
 */
public class LaatikkoTest {
    private Laatikko laatikko;
    
    public LaatikkoTest() {
    }
    
    @Before
    public void setUp() {
        laatikko = new Laatikko(40, 60, 40, 123456789111L);
    }
    
    @Test
    public void leveysAsettuuOikein() {
        assertEquals(40, laatikko.getLeveys());
    }
    
    @Test
    public void pituusAsettuuOikein() {
        assertEquals(60, laatikko.getPituus());
    }
    
    @Test
    public void korkeusAsettuuOikein() {
        assertEquals(40, laatikko.getKorkeus());
    }
    
    @Test
    public void EANasettuuOikein() {
        assertEquals(123456789111L, laatikko.getEAN());
    }
}
