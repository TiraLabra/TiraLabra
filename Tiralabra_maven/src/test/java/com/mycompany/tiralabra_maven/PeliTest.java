package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.gui.Kayttoliittyma;
import java.lang.reflect.InvocationTargetException;
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

    public PeliTest() {
    }

    @Before
    public void setUp() {
        peli = new Peli();
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(peli);
        try {
            SwingUtilities.invokeAndWait(kayttoliittyma);
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        peli.setPiirtoalusta(kayttoliittyma.getPiirtoalusta());
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
    public void luovutaPeliLopettaaAloitetunPelin() {
        peli.uusiPeli();
        peli.luovutaPeli();
        assertEquals(false, peli.isPeliKaynnissa());
    }

    @Test
    public void AISiirtaaVaihtaaSiirtovuoronPelinAlussa() {
        peli.uusiPeli();
        peli.AISiirtaa();
        assertEquals(false, peli.isValkoisenVuoroSiirtaa());
    }

    @Test
    public void AISiirtaaVaihtaaSiirtovuoronPelinAlussaKahdesti() {
        peli.uusiPeli();
        peli.AISiirtaa();
        peli.AISiirtaa();
        assertEquals(true, peli.isValkoisenVuoroSiirtaa());
    }
    
    @Test
    public void valitseRuudutAsettaaValitunRuudunJosSeOnMahdollisissaSiirroissa() {
        peli.uusiPeli();
        peli.valitseRuudutJoissaSiirtoTapahtuu(5, 0);
        assertEquals(5, peli.getValittuRivi());
        assertEquals(0, peli.getValittuSarake());
    }
    
    @Test
    public void valitseRuudutEiAsetaValittuaRuutuaJosSeEiOleMahdollisissaSiirroissa() {
        peli.uusiPeli();
        peli.valitseRuudutJoissaSiirtoTapahtuu(1, 0);
        assertEquals(-1, peli.getValittuRivi());
    }
    
    @Test
    public void valitseRuudutTekeeSiirronJosSeOnMahdollinenSiirronLoppupiste() {
        peli.uusiPeli();
        peli.valitseRuudutJoissaSiirtoTapahtuu(5, 0);
        peli.valitseRuudutJoissaSiirtoTapahtuu(4, 1);
        assertEquals(false, peli.isValkoisenVuoroSiirtaa());
    }
    
    @Test
    public void valitseRuudutEiTeeSiirtoaJosSeEiOleMahdollinenSiirronLoppupiste() {
        peli.uusiPeli();
        peli.valitseRuudutJoissaSiirtoTapahtuu(5, 0);
        peli.valitseRuudutJoissaSiirtoTapahtuu(5, 1);
        assertEquals(true, peli.isValkoisenVuoroSiirtaa());
    }

}
