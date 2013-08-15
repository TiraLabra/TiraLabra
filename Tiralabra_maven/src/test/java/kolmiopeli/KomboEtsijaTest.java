
package kolmiopeli;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.domain.Koordinaatti;
import kolmiopeli.domain.Ruudukko;
import kolmiopeli.logiikka.KolmioTayttaja;
import kolmiopeli.logiikka.tiralabraAlgoritmit.KomboEtsija;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eemi
 */
public class KomboEtsijaTest {
    private Ruudukko lauta;
    private KomboEtsija etsija;
    // Dokumentoinnissa on kuva testiRuudukosta
    static Kolmio[][] testiRuudukko = {{new Kolmio(Color.MAGENTA, 0, 0), new Kolmio(Color.RED, 0, 1), new Kolmio(Color.BLUE, 0, 2), new Kolmio(Color.RED, 0, 3), new Kolmio(Color.RED, 0, 4)},
        {new Kolmio(Color.GREEN, 1, 0), new Kolmio(Color.CYAN, 1, 1), new Kolmio(Color.MAGENTA, 1, 2), new Kolmio(Color.MAGENTA, 1, 3), new Kolmio(Color.RED, 1, 4)},
        {new Kolmio(Color.CYAN, 2, 0), new Kolmio(Color.CYAN, 2, 1), new Kolmio(Color.CYAN, 2, 2), new Kolmio(Color.MAGENTA, 2, 3), new Kolmio(Color.BLUE, 2, 4)},
        {new Kolmio(Color.CYAN, 3, 0), new Kolmio(Color.MAGENTA, 3, 1), new Kolmio(Color.MAGENTA, 3, 2), new Kolmio(Color.MAGENTA, 3, 3), new Kolmio(Color.CYAN, 3, 4)},
    };
    
    public KomboEtsijaTest() {
        lauta = new Ruudukko(4, 5);
        lauta.setRuudukko(testiRuudukko);
        etsija = new KomboEtsija(lauta.getRuudukko());
    }
    
    @Test
    public void palauttaakoViereinenYliYlareunanFalse() {
        Koordinaatti k = new Koordinaatti(0, 1);
        int vRivi = -1;
        int vSarake = 1;
        assertTrue("Ylareunan ylapuolella koordinaatti palauttaa true", !etsija.testViereinenEiOleYliReunan(k, vRivi, vSarake));
    }
    
    @Test
    public void palauttaakoViereinenAliAlareunanFalse() {
        Koordinaatti k = new Koordinaatti(3, 3);
        int vRivi = 4;
        int vSarake = 3;
        assertTrue("Alareunan alapuolella koordinaatti palauttaa true", !etsija.testViereinenEiOleYliReunan(k, vRivi, vSarake));
    }
    
    @Test
    public void palauttaakoViereinenYliVasemmanReunanFalse() {
        Koordinaatti k = new Koordinaatti(2, 0);
        int vRivi = 2;
        int vSarake = -1;
        assertTrue("Vasemman reunan vasemmalla oleva koordinaatti palauttaa true", !etsija.testViereinenEiOleYliReunan(k, vRivi, vSarake));
    }
    
    @Test
    public void palauttaakoViereinenYliOikeanReunanFalse() {
        Koordinaatti k = new Koordinaatti(1, 4);
        int vRivi = 1;
        int vSarake = 5;
        assertTrue("Oikean reunan oikealla oleva koordinaatti palauttaa true", !etsija.testViereinenEiOleYliReunan(k, vRivi, vSarake));
    }
    
    @Test
    public void palauttaakoViereinenNurkassaTrue() {
        Koordinaatti k = new Koordinaatti(0, 1);
        int vRivi = 0;
        int vSarake = 0;
        assertTrue("Nurkassa oleva on muka yli reunan", etsija.testViereinenEiOleYliReunan(k, vRivi, vSarake));
    }
    
    @Test
    public void palauttaakoViereinenEriVarinenFalse() {
        Kolmio viereinen = lauta.getRuudukko()[1][1];
        Koordinaatti tutkittava = new Koordinaatti(1, 2);
        Color tVari = lauta.getRuudukko()[tutkittava.getRivi()][tutkittava.getSarake()].getKolmionVari();
        assertTrue("Viereinen on muka samanvarinen vaikka erivarisia", !etsija.testViereinenOnSamanvarinen(viereinen, tutkittava, tVari));
    }
    
    @Test
    public void palauttaakoViereinenSamanvarinenTrue() {
        Kolmio viereinen = lauta.getRuudukko()[1][3];
        Koordinaatti tutkittava = new Koordinaatti(1, 2);
        Color tVari = lauta.getRuudukko()[tutkittava.getRivi()][tutkittava.getSarake()].getKolmionVari();
        assertTrue("Viereinen on muka erivarinen vaikka samanvarisia", etsija.testViereinenOnSamanvarinen(viereinen, tutkittava, tVari));
    }
    
    @Test
    public void huomaakoEttaViereinenOnKaytyLapi() {
        boolean[][] onkoKaytyLapi = new boolean[lauta.getRuudukko().length][lauta.getRuudukko()[0].length];
        Kolmio viereinen = lauta.getRuudukko()[1][1];
        onkoKaytyLapi[viereinen.getSijaintiRivi()][viereinen.getSijaintiSarake()] = true;
        Koordinaatti tutkittava = new Koordinaatti(2, 1);
        int vRivi = 1;
        int vSarake = 1;
        assertTrue("Viereinen oli kayty lapi mutta sita ei huomattu", !etsija.testViereistaEiOleKaytyLapi(viereinen, tutkittava, vRivi, vSarake, onkoKaytyLapi)); 
    }
    
    @Test
    public void huomaakoEttaViereistaEiOleKaytyLapi() {
        boolean[][] onkoKaytyLapi = new boolean[lauta.getRuudukko().length][lauta.getRuudukko()[0].length];
        Kolmio viereinen = lauta.getRuudukko()[1][1];
        Koordinaatti tutkittava = new Koordinaatti(2, 1);
        int vRivi = 1;
        int vSarake = 1;
        assertTrue("Viereinen oli muka kayty lapi vaikka ei oikeasti oltu", etsija.testViereistaEiOleKaytyLapi(viereinen, tutkittava, vRivi, vSarake, onkoKaytyLapi)); 
    }
    
    @Test
    public void testaaTilanteita1() {
        ArrayList<Koordinaatti> tutkittavat = new ArrayList<Koordinaatti>();
        tutkittavat.add(new Koordinaatti(1, 2));
        tutkittavat.add(new Koordinaatti(0, 2));
        tutkittavat.add(new Koordinaatti(0, 3));
        
        HashSet<Koordinaatti> odotettuTulos = new HashSet<Koordinaatti>();
        odotettuTulos.add(new Koordinaatti(1, 2));
        odotettuTulos.add(new Koordinaatti(1, 3));
        odotettuTulos.add(new Koordinaatti(2, 3));
        odotettuTulos.add(new Koordinaatti(0, 3));
        odotettuTulos.add(new Koordinaatti(0, 4));
        odotettuTulos.add(new Koordinaatti(1, 4));
        
        HashSet<Koordinaatti> loydetyt = etsija.etsiKombot(tutkittavat);
        
        assertTrue("Syotteella " + tutkittavat + " etsija loysi koordinaatit " + loydetyt 
                + " vaikka odotetut koordinaatit olivat " + odotettuTulos,
                odotettuTulos.equals(loydetyt));
    }
    
    @Test
    public void testaaTilanteita2() {
        ArrayList<Koordinaatti> tutkittavat = new ArrayList<Koordinaatti>();
        tutkittavat.add(new Koordinaatti(3, 2));
        tutkittavat.add(new Koordinaatti(3, 3));
        tutkittavat.add(new Koordinaatti(3, 4));
        
        HashSet<Koordinaatti> odotettuTulos = new HashSet<Koordinaatti>();
        odotettuTulos.add(new Koordinaatti(3, 1));
        odotettuTulos.add(new Koordinaatti(3, 2));
        odotettuTulos.add(new Koordinaatti(3, 3));
        
        HashSet<Koordinaatti> loydetyt = etsija.etsiKombot(tutkittavat);
        
        assertTrue("Syotteella " + tutkittavat + " etsija loysi koordinaatit " + loydetyt 
                + " vaikka odotetut koordinaatit olivat " + odotettuTulos,
                odotettuTulos.equals(loydetyt));
    }
    
    @Test
    public void testaaTilanteita3() {
        ArrayList<Koordinaatti> tutkittavat = new ArrayList<Koordinaatti>();
        tutkittavat.add(new Koordinaatti(0, 0));
        tutkittavat.add(new Koordinaatti(0, 1));
        tutkittavat.add(new Koordinaatti(0, 2));
        
        HashSet<Koordinaatti> odotettuTulos = new HashSet<Koordinaatti>();
        
        HashSet<Koordinaatti> loydetyt = etsija.etsiKombot(tutkittavat);
        
        assertTrue("Syotteella " + tutkittavat + " etsija loysi koordinaatit " + loydetyt 
                + " vaikka odotetut koordinaatit olivat " + odotettuTulos,
                odotettuTulos.equals(loydetyt));
    }
    
    @Test
    public void testaaTilanteita4() {
        ArrayList<Koordinaatti> tutkittavat = new ArrayList<Koordinaatti>();
        tutkittavat.add(new Koordinaatti(3, 4));
        tutkittavat.add(new Koordinaatti(3, 2));
        tutkittavat.add(new Koordinaatti(2, 4));
        tutkittavat.add(new Koordinaatti(2, 3));
        tutkittavat.add(new Koordinaatti(2, 2));
        tutkittavat.add(new Koordinaatti(2, 1));
        
        HashSet<Koordinaatti> odotettuTulos = new HashSet<Koordinaatti>();
        odotettuTulos.add(new Koordinaatti(1, 1));
        odotettuTulos.add(new Koordinaatti(1, 2));
        odotettuTulos.add(new Koordinaatti(1, 3));
        odotettuTulos.add(new Koordinaatti(2, 0));
        odotettuTulos.add(new Koordinaatti(2, 1));
        odotettuTulos.add(new Koordinaatti(2, 2));
        odotettuTulos.add(new Koordinaatti(2, 3));
        odotettuTulos.add(new Koordinaatti(3, 0));
        odotettuTulos.add(new Koordinaatti(3, 1));
        odotettuTulos.add(new Koordinaatti(3, 2));
        odotettuTulos.add(new Koordinaatti(3, 3));
        
        HashSet<Koordinaatti> loydetyt = etsija.etsiKombot(tutkittavat);
        
        assertTrue("Syotteella " + tutkittavat + " etsija loysi koordinaatit " + loydetyt 
                + " vaikka odotetut koordinaatit olivat " + odotettuTulos,
                odotettuTulos.equals(loydetyt));
    }
    
}
    
