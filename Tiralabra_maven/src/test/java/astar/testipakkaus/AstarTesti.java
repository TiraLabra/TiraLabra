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
        this.kartta = new Kartta(4,2,null);
        
        this.astar = new Astar(kartta);
        
    }
    @Test
    public void testaaReittiossi(){
        
        
        
    }
    
  

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
