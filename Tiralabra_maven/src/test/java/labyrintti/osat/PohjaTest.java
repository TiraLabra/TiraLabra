package labyrintti.osat;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author heidvill
 */
public class PohjaTest {

    /**
     * Testattava pohja.
     */
    private Pohja pohja;
    /**
     * Testikartta.
     */
    private String esim;

    @Before
    public void setUp() {
        esim = "L11"
            + "111"
            + "11M";
        pohja = new Pohja(3, 3, esim);
    }

    @Test
    public void pohjaLuodaanOikein() {
        assertEquals(3, pohja.getKorkeus());
        assertEquals(3, pohja.getLeveys());
    }

    @Test
    public void lahtoOikein() {
        assertEquals(0, pohja.getLahtoX());
        assertEquals(0, pohja.getLahtoY());
    }

    @Test
    public void maaliOikein() {
        assertEquals(2, pohja.getMaaliX());
        assertEquals(2, pohja.getMaaliY());
    }
}
