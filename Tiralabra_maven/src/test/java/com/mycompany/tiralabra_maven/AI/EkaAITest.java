package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.peli.PelaajaTyyppi;
import com.mycompany.tiralabra_maven.peli.Peli;
import com.mycompany.tiralabra_maven.peli.PeliOhjain;
import com.mycompany.tiralabra_maven.peli.Pelilauta;
import com.mycompany.tiralabra_maven.peli.Siirto;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author noora
 */
public class EkaAITest {

    private PeliOhjain peliOhjain;
    private Pelilauta pelilauta;
    private Peli peli;
    private EkaAI AI;

    public EkaAITest() {
    }

    @Before
    public void setUp() {
        this.pelilauta = new Pelilauta();
        this.pelilauta.asetaNappulatAlkuasetelmaan();
        
        peliOhjain = mock(PeliOhjain.class);
        peli = mock(Peli.class);
        AI = new EkaAI(peli, peliOhjain, false, 0);
    }

    @Test
    public void aiEiSiirraAutomaagisestiJosOnPeliohjain() {
        AI.seuraavaSiirto(pelilauta.getSallitutSiirrot(true));
        verify(peliOhjain).odotaAiNapinPainamista();
    }

    @Test
    public void aiSiirtaaAutomaagisestiJosEiOlePeliohjainta() {
        EkaAI uusiAI = new EkaAI(null, null, false, 0);
        assertEquals(true, uusiAI.isSiirraAutomaagisesti());
    }

    @Test
    public void seuraavaSiirtoPalauttaaOikeanSiirronPelinAlussa() {
        Siirto siirto = AI.seuraavaSiirto(pelilauta.getSallitutSiirrot(true));
        
        assertEquals(4, siirto.getLoppuRivi());
        assertEquals(1, siirto.getLoppuSarake());
    }

}
