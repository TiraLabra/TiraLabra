package astar.testipakkaus;

import astar.verkko.Solmu;
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
    @Test
    public void equalstesti(){
        boolean onkoSama;
        onkoSama = solmu.equals(this.solmu);
        assertEquals(onkoSama, true);
    }
    @Test
    public void equalstesti2(){
        Solmu solmu3 = new Solmu(0,0,null,0);
        boolean onkoSama;
        onkoSama = solmu.equals(solmu3);
        
        assertEquals(onkoSama, true);
    }
      @Test
    public void equalstesti3(){
        Solmu solmu3 = new Solmu(0,0,null,0);
        boolean onkoSama;
        onkoSama = solmu.equals(solmu2);
        
        assertEquals(onkoSama, false);
    }
    

}
