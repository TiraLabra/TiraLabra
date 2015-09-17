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
    Solmu solmu2;

    public Solmutesti() {

    }

    @Before
    public void setUp() {
        solmu = new Solmu(0, 0, null, 0);
        solmu2 = new Solmu(1, 0, solmu, 1);
    }


    @Test
    public void testaaMatkaAlusta(){
       
        assertEquals(1, solmu2.getMatkaAlusta());
    }
    @Test
    public void testaaEdellinen(){
        assertEquals(solmu, solmu2.getEdellinen());
    }
    

}
