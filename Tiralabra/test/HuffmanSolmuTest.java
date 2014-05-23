
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HuffmanSolmuTest {
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void alustetaanOikein() {
        HuffmanSolmu solmu = new HuffmanSolmu(1, 12, null, null);
        
        assertEquals(solmu.getMaara(), 12);
        assertEquals(solmu.getMerkki(), 1);
        assertNull(solmu.getOikea());
        assertNull(solmu.getVasen());
    }
    
    @Test
    public void vertailuToimii() {
        HuffmanSolmu solmu = new HuffmanSolmu(2, 24, null, null);
        
        assertTrue(solmu.compareTo(new HuffmanSolmu(1, 34, null, null)) < 0);        
        assertTrue(solmu.compareTo(new HuffmanSolmu(1, 24, null, null)) > 0);
    }
}