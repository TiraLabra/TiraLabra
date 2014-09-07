
package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.peli.Peli;
import com.mycompany.tiralabra_maven.peli.PeliOhjain;
import com.mycompany.tiralabra_maven.peli.Pelilauta;
import com.mycompany.tiralabra_maven.peli.Siirto;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class MinimaxAlfaBetaAITest {
    
    private PeliOhjain peliOhjain;
    private Pelilauta pelilauta;
    private Peli peli;
    private MinimaxAlfaBetaAI AI;
    
    public MinimaxAlfaBetaAITest() {
    }
    
    @Before
    public void setUp() {
        this.pelilauta = new Pelilauta();
        this.pelilauta.asetaNappulatAlkuasetelmaan();
        
        peliOhjain = mock(PeliOhjain.class);
        peli = mock(Peli.class);
        when(peli.getPelilauta()).thenReturn(pelilauta);
        when(peli.isValkoisenVuoroSiirtaa()).thenReturn(Boolean.TRUE);
        AI = new MinimaxAlfaBetaAI(peli, peliOhjain, false, 0, 5);
    }

    @Test
    public void seuraavaSiirtoPalauttaaOikeanSiirronPelinAlussa() {
        Siirto siirto = AI.seuraavaSiirto(pelilauta.getSallitutSiirrot(true));
        
        assertEquals(4, siirto.getLoppuRivi());
        assertEquals(1, siirto.getLoppuSarake());
    }
    
}
