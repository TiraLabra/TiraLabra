package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.gui.Paivitettava;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author noora
 */
public class PeliTest {

    private Peli peli;
    StubPaivitettava p;

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

    public PeliTest() {
    }

    @Before
    public void setUp() {
        peli = new Peli();
        
        p = new StubPaivitettava();

        peli.setPaivitettava(p);
        
    }

    @Test
    public void uusiPeliKaynnistaaPelin() {
        peli.uusiPeli();
        assertEquals(true, peli.isPeliKaynnissa());
    }

    @Test
    public void uusiPeliAntaaSiirtovuoronValkoiselle() {
        peli.uusiPeli();
        assertEquals(true, peli.isValkoisenVuoroSiirtaa());
    }
    
    @Test
    public void uusiPeliNayttaaViestinPelaajalle() {
        peli.uusiPeli();
        assertEquals("Valkoisen vuoro siirtää!", p.getViesti());
    }

    @Test
    public void luovutaPeliLopettaaAloitetunPelin() {
        peli.uusiPeli();
        peli.luovutaPeli();
        assertEquals(false, peli.isPeliKaynnissa());
    }
    
    @Test
    public void luovutaPeliNayttaaViestinKayttajalle() {
        peli.uusiPeli();
        peli.luovutaPeli();
        assertEquals("Valkoinen luovutti. Musta voitti!", p.getViesti());
    }
    
    @Test
    public void AIsiirtaaNayttaaOikeanViestinJosPeliEiOleKaynnissa() {
        peli.AISiirtaa();
        assertEquals("Aloita ensin uusi peli!", p.getViesti());
    }

}
