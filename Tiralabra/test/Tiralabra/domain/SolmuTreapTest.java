/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tiralabra.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pppakari
 */
public class SolmuTreapTest {
    
    SolmuTreap s;
    
    public SolmuTreapTest() {
    }
    
    @Before
    public void setUp() {
        s = new SolmuTreap(1, 1, null);
    }
    
    @Test
    public void solmulleVanhempi(){
        s.setVanhempi(new SolmuTreap(2, 4, null));
        assertEquals(2, s.getVanhempi().getArvo());
    }
    
    @Test 
    public void solmunPrioriteetti(){
        assertEquals(1, s.getPrioriteetti());
        s.setPrioriteetti(7);
        assertEquals(7, s.getPrioriteetti());
    }
    
    @Test
    public void solmunVasenKakara(){
        assertEquals(null, s.getVasen());
        s.setVasen(new SolmuTreap(9, 8, null));
        assertEquals(8, s.getVasen().getPrioriteetti());
        assertEquals(s, s.getVasen().getVanhempi());
    }
    
    @Test
    public void solmunOikeaKakara(){
        assertEquals(null, s.getOikea());
        s.setOikea(new SolmuTreap(10, 4, null));
        assertEquals(4, s.getOikea().getPrioriteetti());
        assertEquals(s, s.getOikea().getVanhempi());
    }
    
    @Test
    public void solmuArvo(){
        assertEquals(1, s.getArvo());
        s.setArvo(99);
        assertEquals(99, s.getArvo());
    }
}
