package labyrintti;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author heidvill
 */
public class KaynnistysTest {

    private Kaynnistys kaynnistys;

    public KaynnistysTest() {
    }

    @Before
    public void setUp() {
        kaynnistys = new Kaynnistys();
        kaynnistys.valmisPohja();
    }

    @Test
    public void kaynnistysAsettaaPohjanOikein() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 0 && j == 0 || (i == 2 && j == 2)) {
                    assertEquals(0, kaynnistys.getPohja().getKartta()[i][j].getArvo());
                } else {
                    assertEquals(1, kaynnistys.getPohja().getKartta()[i][j].getArvo());
                }
            }
        }
    }

    @Test
    public void kaynnistysAsettaaEtsijanOikein() {
        assertEquals(null, kaynnistys.getEtsija().getReitti());
    }
}