/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OhjelmaLogiikka.Pakkaaja;

import TestiTiedostoLuokat.TestiKirjoittaja;
import TestiTiedostoLuokat.TestiLukija;
import Tietorakenteet.TiedostoBlokki;
import Tietorakenteet.HuffmanKoodi;
import Tietorakenteet.OmaHashMap;
import Tietorakenteet.OmaMap;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Omistaja
 */
public class TiivistajaTest {

    private Tiivistaja tiivistaja;
    private TestiLukija lukija;
    private TestiKirjoittaja kirjoittaja;
    private OmaMap<TiedostoBlokki, HuffmanKoodi> koodit;

    public TiivistajaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tiivistaja = new Tiivistaja(1);
        lukija = new TestiLukija();
        lukija.tavut = "abcde".getBytes();
        kirjoittaja = new TestiKirjoittaja();
        koodit = new OmaHashMap<TiedostoBlokki, HuffmanKoodi>();
        luoKoodit();
    }

    @After
    public void tearDown() {
    }

    @Test(expected=NullPointerException.class)
    public void tiivistajaHeittaaNullPointerExceptionJosKoodiPuuttuu() {
        try {
            lukija.tavut = "abcdefghijklmno".getBytes();
            tiivistaja.tiivista(lukija, kirjoittaja, koodit);
           
        } catch (IOException ex) {
            assertTrue("Tätä ei pitäisi tapahtua...", false);
        }
    }
    

    @Test
    public void tiivistajaToimiiOikein() {
        try {
            tiivistaja.tiivista(lukija, kirjoittaja, koodit);
            // pitäisi olla a -> 00, b -> 001, c -> 010
            // tallennetaan tavuun alusta loppuun eli c - b - a -> 010 - 001 - 00 -> 01000100 = 64 + 4 = 68
            assertEquals("Ensimmäisen tavun arvo väärä", 68, kirjoittaja.haeTavut()[0]);
            // pitäisi olla d -> 0100, e -> 1000
            // tallennetaan tavuun alusta loppuun eli e - d -> 1000 - 0100 -> 10000100 = -128 + 4 = -124
            assertEquals("Toisen tavun arvo väärä", -124, kirjoittaja.haeTavut()[1]);
        } catch (IOException ex) {
            assertTrue("Tätä ei pitäisi tapahtua...", false);
        }
    }

    @Test
    public void tiivistajaAntaaOikeanTiedostoKoon() {
        try {
            tiivistaja.tiivista(lukija, kirjoittaja, koodit);
            assertEquals("Tiivistäjä antaa tiedostokoon väärin", 2, tiivistaja.haeTiedostonKoko());
        } catch (IOException ex) {
            assertTrue("Tätä ei pitäisi tapahtua...", false);
        }
    }

    @Test
    public void tiivistajaPalauttaaOikeanMerkitsevienBittienMäärän() {
        try {
            assertEquals("Tiivistäjä antaa tiedostokoon väärin", 8, tiivistaja.tiivista(lukija, kirjoittaja, koodit));
        } catch (IOException ex) {
            assertTrue("Tätä ei pitäisi tapahtua...", false);
        }
    }

    private void luoKoodit() {
        TiedostoBlokki wrapper;
        HuffmanKoodi koodi;

        wrapper = new TiedostoBlokki();
        koodi = new HuffmanKoodi();
        wrapper.byteTaulukko = new byte[]{'a'};
        koodi.pituus = 2;
        koodi.koodi = 0;
        koodit.put(wrapper, koodi);

        wrapper = new TiedostoBlokki();
        koodi = new HuffmanKoodi();
        wrapper.byteTaulukko = new byte[]{'b'};
        koodi.pituus = 3;
        koodi.koodi = 1;
        koodit.put(wrapper, koodi);

        wrapper = new TiedostoBlokki();
        koodi = new HuffmanKoodi();
        wrapper.byteTaulukko = new byte[]{'c'};
        koodi.pituus = 3;
        koodi.koodi = 2;
        koodit.put(wrapper, koodi);

        wrapper = new TiedostoBlokki();
        koodi = new HuffmanKoodi();
        wrapper.byteTaulukko = new byte[]{'d'};
        koodi.pituus = 4;
        koodi.koodi = 4;
        koodit.put(wrapper, koodi);

        wrapper = new TiedostoBlokki();
        koodi = new HuffmanKoodi();
        wrapper.byteTaulukko = new byte[]{'e'};
        koodi.pituus = 4;
        koodi.koodi = 8;
        koodit.put(wrapper, koodi);




    }
}
