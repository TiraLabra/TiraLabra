/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.testipakkaus;

import astar.logiikka.Astar;
import astar.verkko.Kartta;
import astar.verkko.Solmu;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sasumaki
 */
public class AstarTesti {
    private final Kartta kartta;
    private final Astar astar;
    public AstarTesti() {
        this.kartta = new Kartta(4,2);
        kartta.luoKartta();
        this.astar = new Astar(kartta);
        
    }
    @Test
    public void testaaReittiossi(){
        Solmu s; 
        
        String o = "-+++";
        
        assertEquals(s = astar.haku(1, 0, 3, 0), o);
        
    }
    
  

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
