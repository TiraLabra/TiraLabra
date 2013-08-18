package Tiralabra.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class SolmuBTest {

    SolmuB s;
    
    public SolmuBTest() {
    }
    
    @Before
    public void setUp() {
        s = new SolmuB(6, null);
    }
    
    @Test
    public void setteritgetterit(){
        assertEquals(6, s.getEnsimmainenArvo());
        assertEquals(1, s.solmunKoko());
        s.setKeski(new SolmuB(5, s));
        s.setOikea(new SolmuB(7, s));
        s.setVasen(new SolmuB(3, s));
        assertEquals(3, s.getVasen().getEnsimmainenArvo());
        assertEquals(5, s.getKeski().getEnsimmainenArvo());
        assertEquals(7, s.getOikea().getEnsimmainenArvo());
        assertEquals(s, s.getOikea().getParent());
        s.setParent(new SolmuB(9, null));
        assertEquals(9, s.getParent().getEnsimmainenArvo());
        s.lisaaArvo(8);
        assertEquals(8, s.getToinenArvo());
        assertEquals(2, s.solmunKoko());
    }
    
    @Test
    public void arvonLisaaminen(){
        s.lisaaArvo(1);
        assertEquals(1, s.getEnsimmainenArvo());
    }
    
    @Test
    public void arvoPois(){
        s.lisaaArvo(8);
        assertEquals(8, s.poistaArvo(8));
        assertEquals(6, s.getEnsimmainenArvo());
    }
    
    @Test
    public void olematonArvoPois(){
        assertEquals(-7, s.poistaArvo(7));
    }
}