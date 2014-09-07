
package com.mycompany.tiralabra_maven.tietorakenteet;

import com.mycompany.tiralabra_maven.peli.Siirto;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SolmuTest {
    private Solmu solmu;
    
    public SolmuTest() {
    }
    
    @Before
    public void setUp() {
        solmu = new Solmu(5, new Siirto(1,2,3,4));
    }

    @Test
    public void getArvoPalauttaaArvon() {
        assertEquals(5, solmu.getArvo());
    }

    @Test
    public void getSiirtoPalauttaaSiirron() {
        assertEquals(2, solmu.getSiirto().getAlkuSarake());
        assertEquals(3, solmu.getSiirto().getLoppuRivi());
    }
    
}
