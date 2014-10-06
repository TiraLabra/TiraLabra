package wad.solmu;

import wad.solmu.Solmu;
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
        solmuA = new Solmu("a");
        solmuB = new Solmu("b");
        solmuC = new Solmu("c");
    }
        
    @Test
    public void tarkistaSolmuA() {
        assertEquals("a", solmuA.getArvo());
        assertNotNull(solmuA.getAvain());
    }
    
    @Test
    public void tarkistaSolmuB() {
        assertEquals("b", solmuB.getArvo());
        assertNotNull(solmuB.getAvain());
    }
    
    @Test
    public void tarkistaSolmuC() {
        assertEquals("c", solmuC.getArvo());
        assertNotNull(solmuC.getAvain());
    }
    
    @Test 
    public void lapsetonSolmu() {
        assertNull(solmuA.getOikea());
        assertNull(solmuA.getVasen());
        assertTrue(solmuA.lapseton());
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
    
    @Test
    public void solmullaVanhempi() {
        solmuA.setVanhempi(solmuB);
        assertEquals(solmuB, solmuA.getVanhempi());
    }
    
    @Test
    public void solmunKorkeus() {
        solmuA.setKorkeus(2);
        assertEquals(2, solmuA.getKorkeus());
    }
    
    @Test
    public void solmuOnMusta() {
        solmuA.setMusta();
        assertTrue(solmuA.onMusta());
    }
    
    @Test
    public void solmuOnPunainen() {
        solmuA.setPunainen();
        assertFalse(solmuA.onMusta());
    }
}
