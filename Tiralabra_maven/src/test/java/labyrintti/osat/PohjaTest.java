package labyrintti.osat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author heidvill@cs
 */
public class PohjaTest {
    private Pohja pohja;
    
    public PohjaTest() {
    }
    
    @Before
    public void setUp() {
        pohja = new Pohja();
        pohja.alustaPohja1("src/main/java/labyrintti/osat/kartta2.txt");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void pohjaLuodaanOikein() {
        assertEquals(2, pohja.getKorkeus());
        assertEquals(4, pohja.getLeveys());
    }
    
    @Test
    public void lahtoOikein(){
        assertEquals(0, pohja.getLahtoX());
        assertEquals(3, pohja.getLahtoY());
    }
    
    @Test
    public void maaliOikein(){
        assertEquals(1, pohja.getMaaliX());
        assertEquals(0, pohja.getMaaliY());
    }
    
}
