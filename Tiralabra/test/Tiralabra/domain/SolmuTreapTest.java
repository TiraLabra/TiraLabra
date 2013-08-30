package Tiralabra.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SolmuTreapTest {
    
    SolmuTreap s;
    
    public SolmuTreapTest() {
    }
    
    @Before
    public void setUp() {
        s = new SolmuTreap(1, null);
    }
    
    @Test
    public void solmulleVanhempi(){
        s.setVanhempi(new SolmuTreap(2, null));
        assertEquals(2, s.getVanhempi().getArvo());
    }
    
    @Test
    public void solmunVasenKakara(){
        assertEquals(null, s.getVasen());
        s.setVasen(new SolmuTreap(9, null));
        assertEquals(s, s.getVasen().getVanhempi());
    }
    
    @Test
    public void solmunOikeaKakara(){
        assertEquals(null, s.getOikea());
        s.setOikea(new SolmuTreap(10, null));
        assertEquals(s, s.getOikea().getVanhempi());
    }
    
    @Test
    public void solmuArvo(){
        assertEquals(1, s.getArvo());
        s.setArvo(99);
        assertEquals(99, s.getArvo());
    }
    
}