package Tiralabra.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SolmuThreadedTest {

    SolmuThreaded s;
    
    public SolmuThreadedTest() {
    }

    @Before
    public void setUp() {
        s = new SolmuThreaded(1);
    }
    
    @Test
    public void uusiSolmu(){
        assertEquals(1, s.getKey());
        assertEquals(null, s.getParent());
        assertEquals(null, s.getOikea());
        assertEquals(null, s.getVasen());
    }
    
    @Test
    public void parent(){
        SolmuThreaded p = new SolmuThreaded(9);
        s.setParent(p);
        assertEquals(p, s.getParent());
    }
    
    @Test
    public void arvo() {
        assertEquals(1, s.getKey());
        s.setKey(10);
        assertEquals(10, s.getKey());
    }
    
    @Test 
    public void lastenLisaaminen(){
        s.setOikea(new SolmuThreaded(2));
        assertEquals(2, s.getOikea().getKey());
        s.setVasen(new SolmuThreaded(0));
        assertEquals(0, s.getVasen().getKey());
    }
    
    @Test
    public void lastenStatus() {
        s.oikeanLapsenStatusSet(true);
        assertEquals(true, s.oikeaStatusGet());
        assertEquals(false, s.vasenStatusGet());
        s.oikeanLapsenStatusSet(false);
        s.vasemmanLapsenStatusSet(true);
        assertEquals(false, s.oikeaStatusGet());
        assertEquals(true, s.vasenStatusGet());
    }
    
}
