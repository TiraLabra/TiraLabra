package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.Pelilauta;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author noora
 */
public class HeuristiikkaTest {
    private Pelilauta lauta;
    private Heuristiikka heuristiikka;
    
    public HeuristiikkaTest() {
    }
    
    @Before
    public void setUp() {
        lauta = new Pelilauta();
        lauta.asetaNappulatAlkuasetelmaan();
        heuristiikka = new Heuristiikka(lauta);
    }

    @Test
    public void laskeTilanteenArvoToimiiAlkuasetelmassa() {
        assertEquals(61, heuristiikka.laskeTilanteenArvo(true));
    }
    
}
