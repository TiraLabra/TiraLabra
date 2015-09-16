package astar.testipakkaus;

import astar.astar.Solmu;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class Solmutesti {

    Solmu solmu;

    public Solmutesti() {

    }

    @Before
    public void setUp() {
        solmu = new Solmu(0, 0, false, false, false);
    }

    @Test
    public void testaaEsteys() {
        solmu.setEste(true);
        assertEquals(true, solmu.getEsteys());
    }
    @Test
    public void testaaMaaleus() {
        solmu.setMaali(true);
        assertEquals(true, solmu.getMaaleus());
    }
    @Test
    public void testaaAlku() {
        solmu.setAlku(true);
        assertEquals(true, solmu.getAlkeus());
    }
    @Test
    public void testaaMatkaAlusta(){
        solmu.setMatkaAlusta(25);
        assertEquals(25, solmu.getMatkaAlusta());
    }
    

}
