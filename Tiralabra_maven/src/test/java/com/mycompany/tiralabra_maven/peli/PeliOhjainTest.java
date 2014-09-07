package com.mycompany.tiralabra_maven.peli;

import com.mycompany.tiralabra_maven.peli.PelaajaTyyppi;
import com.mycompany.tiralabra_maven.peli.PeliOhjain;
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
public class PeliOhjainTest {

    private PeliOhjain peli;
    StubPaivitettava p;

    private class StubPaivitettava implements Paivitettava {
        private String viesti;

        @Override
        public void paivita() {
        }

//        @Override
//        public void naytaViesti(String viesti) {
//            this.viesti = viesti;
//        }
        
        public String getViesti() {
            return this.viesti;
        }

    }

    public PeliOhjainTest() {
    }

    @Before
    public void setUp() {
        peli = new PeliOhjain();
        
        p = new StubPaivitettava();

        peli.setPaivitettava(p);
        
    }

    @Test
    public void uusiPeliKaynnistaaPelin() {
        peli.uusiPeli(PelaajaTyyppi.EKAAI, PelaajaTyyppi.EKAAI);
        assertEquals(true, peli.isPeliKaynnissa());
    }

    @Test
    public void luovutaPeliLopettaaAloitetunPelin() {
        peli.uusiPeli(PelaajaTyyppi.EKAAI, PelaajaTyyppi.EKAAI);
        peli.luovutaPeli();
        assertEquals(false, peli.isPeliKaynnissa());
    }
    
    @Test
    public void uudenPelinAloitusLuoPelilaudan(){
        assertTrue(peli.getPelilauta() == null);
        peli.uusiPeli(PelaajaTyyppi.EKAAI, PelaajaTyyppi.EKAAI);
        assertTrue(peli.getPelilauta() != null);
    }

}
