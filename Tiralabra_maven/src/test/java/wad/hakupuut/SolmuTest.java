package wad.hakupuut;

import wad.hakupuut.Solmu;
import junit.framework.TestCase;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SolmuTest {
    public Solmu solmuA, solmuB, solmuC;
    
    public SolmuTest(){
    }

    @Before
    public void setUp() {
        solmuA = new Solmu(1, "a");
        solmuB = new Solmu(2, "b");
        solmuC = new Solmu(3, "c");
    }
        
    @Test
    public void tarkistaSolmuA() {
        assertEquals("a", solmuA.getArvo());
        assertEquals(1, solmuA.getAvain());
    }
    
    @Test
    public void tarkistaSolmuB() {
        assertEquals("b", solmuB.getArvo());
        assertEquals(2, solmuB.getAvain());
    }
    
    @Test
    public void tarkistaSolmuC() {
        assertEquals("c", solmuC.getArvo());
        assertEquals(3, solmuC.getAvain());
    }
    
    @Test 
    public void lapsetonSolmu() {
        assertNull(solmuA.getOikea());
        assertNull(solmuA.getVasen());
    }
    
    @Test
    public void solmullaOnVainVasenLapsi() {
        solmuA.setOikea(solmuC);
        assertEquals(solmuC, solmuA.getOikea());
        assertNull(solmuA.getVasen());
    }
    
    @Test
    public void solmullaOnKaksiLasta() {
        solmuA.setOikea(solmuB);
        solmuA.setVasen(solmuC);
        assertEquals(solmuB, solmuA.getOikea());
        assertEquals(solmuC, solmuA.getVasen());
    }
}
