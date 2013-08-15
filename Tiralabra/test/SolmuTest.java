
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SolmuTest {

    private Labyrintti laby;
    private Solmu solmuSeina;
    private Solmu solmuLattia;

    public SolmuTest() {
    }

    @Before
    public void setUp() {
        laby = new Labyrintti("labyTest5x5");
        solmuSeina = laby.verkko[0][1];
        solmuLattia = laby.verkko[0][0];
    }

    @Test
    public void testOnkoSeina() {
        assertFalse(solmuLattia.seina);
        assertTrue(solmuSeina.seina);
    }

    @Test
    public void testGetXJaY() {
        assertTrue(solmuLattia.getX() == 0);
        assertTrue(solmuLattia.getY() == 0);
        assertTrue(solmuSeina.getX() == 1);
        assertTrue(solmuSeina.getY() == 0);
    }

    @Test
    public void testVierusX() {
        assertTrue(solmuLattia.vierusX(-1) == null);
        assertTrue(solmuLattia.vierusX(1) == solmuSeina);
        assertTrue(solmuSeina.vierusX(-1) == solmuLattia);
        assertTrue(laby.verkko[0][4].vierusX(1) == null);
    }

    @Test
    public void testVierusY() {
        assertTrue(solmuLattia.vierusY(-1) == null);
        assertTrue(solmuLattia.vierusY(1) == laby.verkko[1][0]);
        assertTrue(laby.verkko[4][0].vierusY(1) == null);
    }

    @Test
    public void testGetAlkuarvo() {
        for (int i = 0; i < laby.verkko[0].length; i++) {
            for (int j = 0; j < laby.verkko.length; j++) {
                assertTrue(laby.verkko[i][j].getAlkuarvo() == Integer.MAX_VALUE);
            }
        }
    }
}
