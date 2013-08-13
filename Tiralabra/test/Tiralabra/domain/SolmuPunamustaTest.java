package Tiralabra.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Ei testaa yleis-gettereitä/settereitä - testaus suoritettu jo luokalle SolmuThreaded.
 * @author Pia Pakarinen
 */
public class SolmuPunamustaTest {
    
    SolmuPunamusta s;
    
    public SolmuPunamustaTest() {
    }
    
    @Before
    public void setUp() {
        s = new SolmuPunamusta(10, false);
    }
    
    @Test
    public void solmunVari(){
        assertEquals(false, s.getVari());
        s.setVari(true);
        assertEquals(true, s.getVari());
    }
}
