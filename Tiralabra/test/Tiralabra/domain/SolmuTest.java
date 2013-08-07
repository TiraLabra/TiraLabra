package Tiralabra.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SolmuTest {
    
    Solmu s;
    
    public SolmuTest() {
    }
    
    @Before
    public void setUp() {
        s = new Solmu(1);
    }
    
    @Test
    public void solmuLuotuOikeallaArvollaNullLapsilla(){
        assertEquals(1, s.getKey());
        assertEquals(null, s.getOikea());
        assertEquals(null, s.getVasen());
    }
    
    @Test 
    public void solmuSaaOikeanLapsen(){
        s.setOikea(new Solmu(2));
        assertEquals(2 , s.getOikea().getKey());
    }
    
    @Test
    public void solmuSaaVasemmanLapsen() {
        s.setVasen(new Solmu(3));
        assertEquals(3, s.getVasen().getKey());
    }
}