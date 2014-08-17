package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.gui.Paivitettava;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author noora
 */
public class PelaajaTest {

    private Pelaaja pelaaja;
    private Peli peli;
    private Paivitettava paivitettava;
    
    private class StubPaivitettava implements Paivitettava {
        private String viesti;

        @Override
        public void paivita() {
        }

        @Override
        public void naytaViesti(String viesti) {
            this.viesti = viesti;
        }
        
        public String getViesti() {
            return this.viesti;
        }

    }

    public PelaajaTest() {
    }

    @Before
    public void setUp() {
        peli = new Peli();
        paivitettava = new StubPaivitettava();
        peli.setPaivitettava(paivitettava);
        pelaaja = new Pelaaja(peli);
    }

        @Test
    public void valitseRuudutAsettaaValitunRuudunJosSeOnMahdollisissaSiirroissa() {
        peli.uusiPeli();
        pelaaja.valitseRuudutJoissaSiirtoTapahtuu(5, 0);
        assertEquals(5, pelaaja.getValittuRivi());
        assertEquals(0, pelaaja.getValittuSarake());
    }

    @Test
    public void valitseRuudutEiAsetaValittuaRuutuaJosSeEiOleMahdollisissaSiirroissa() {
        peli.uusiPeli();
        pelaaja.valitseRuudutJoissaSiirtoTapahtuu(1, 0);
        assertEquals(-1, pelaaja.getValittuRivi());
    }

    @Test
    public void valitseRuudutTekeeSiirronJosSeOnMahdollinenSiirronLoppupiste() {
        peli.uusiPeli();
        pelaaja.valitseRuudutJoissaSiirtoTapahtuu(5, 0);
        pelaaja.valitseRuudutJoissaSiirtoTapahtuu(4, 1);
        assertEquals(Nappula.VALKOINEN, peli.getPelilauta().getNappula(4, 1));
    }

    @Test
    public void valitseRuudutEiTeeSiirtoaJosSeEiOleMahdollinenSiirronLoppupiste() {
        peli.uusiPeli();
        pelaaja.valitseRuudutJoissaSiirtoTapahtuu(5, 0);
        pelaaja.valitseRuudutJoissaSiirtoTapahtuu(5, 1);
        assertEquals(Nappula.VALKOINEN, peli.getPelilauta().getNappula(5, 0));
        assertEquals(null, peli.getPelilauta().getNappula(5, 1));
    }

}
