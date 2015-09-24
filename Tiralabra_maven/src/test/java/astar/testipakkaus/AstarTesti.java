/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.testipakkaus;

import astar.astar.Astar;
import astar.astar.Kartta;
import astar.astar.Solmu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sasumaki
 */
public class AstarTesti {
    private Kartta kartta;
    private Astar astar;
    public AstarTesti() {
        this.kartta = new Kartta(3,1);
        kartta.luoKartta();
        this.astar = new Astar(kartta);
        
    }
    @Test
    public void testaaReittiossi(){
        Solmu s = astar.haku(0, 0, 2, 0);
        
    }
    
  

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
