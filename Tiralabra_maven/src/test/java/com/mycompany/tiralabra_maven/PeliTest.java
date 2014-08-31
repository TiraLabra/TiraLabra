
package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.AI.EkaAI;
import com.mycompany.tiralabra_maven.AI.AI;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author noora
 */
public class PeliTest {
    private Peli peli;
    
    public PeliTest() {
    }
    
    @Before
    public void setUp() {
        peli = new Peli();
    }
    
    @Test
    public void pelinLuontiLuoPelilaudanJaAsettaaNappulatAlkuasetelmaan(){
        assertEquals(Nappula.MUSTA, peli.getPelilauta().getNappula(0, 1));
        assertEquals(Nappula.VALKOINEN, peli.getPelilauta().getNappula(5, 0));
    }
    
    @Test
    public void pelinLuontiEiKaynnistaPelia(){
        assertEquals(false, peli.isPeliKaynnissa());
    }
    
    @Test
    public void pelinLuontiKysyyEnsimmaisetSallitutSiirrot(){
        assertEquals(7, peli.getSallitutSiirrot().length);
    }
    
    @Test
    public void getMustaPalauttaaOikeanPelaajan(){
        EkaAI pelaaja = new EkaAI(peli, null, true, 0);
        peli.setMusta(pelaaja);
        assertEquals(pelaaja, peli.getMusta());
    }
    
    @Test
    public void getValkoinenPalauttaaOikeanPelaajan(){
        EkaAI pelaaja = new EkaAI(peli, null, true, 0);
        peli.setValkoinen(pelaaja);
        assertEquals(pelaaja, peli.getValkoinen());
    }
    
    @Test
    public void peliEiKaynnistyJosPelaajiaEiOleAsetettu(){
        peli.run();
        assertEquals(false, peli.isPeliKaynnissa());
    }
    
    @Test
    public void peliEiKaynnistyJosOnVainYksiPelaaja(){
        peli.setMusta(new EkaAI(peli, null, true, 0));
        peli.run();
        assertEquals(false, peli.isPeliKaynnissa());
    }
    
    

}
