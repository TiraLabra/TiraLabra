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
        s = new SolmuB(6);
    }
    
    @Test
    public void setteritgetterit(){
        assertEquals("6\n", s.getSolmunarvot().toString());
        s.setKeski(new SolmuB(5));
        s.setOikea(new SolmuB(7));
        s.setVasen(new SolmuB(3));
        assertEquals("3\n", s.getVasen().getSolmunarvot().toString());
        assertEquals("5\n", s.getKeski().getSolmunarvot().toString());
        assertEquals("7\n", s.getOikea().getSolmunarvot().toString());
    }
    
    @Test
    public void arvonLisaaminen(){
        s.lisaaArvo(7);
        s.lisaaArvo(1);
        assertEquals("1\n6\n7\n", s.getSolmunarvot().toString());
    }
    
    @Test
    public void arvoPois(){
        s.lisaaArvo(8);
        assertEquals(8, s.poistaArvo(8));
        assertEquals("6\n", s.getSolmunarvot().toString());
    }
    
    @Test
    public void olematonArvoPois(){
        assertEquals(-7, s.poistaArvo(7));
    }
}