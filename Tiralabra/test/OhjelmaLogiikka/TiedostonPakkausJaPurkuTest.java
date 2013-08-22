/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OhjelmaLogiikka;

import OhjelmaLogiikka.Pakkaaja.KoodiMuodostaja;
import OhjelmaLogiikka.Pakkaaja.Tiivistaja;
import OhjelmaLogiikka.Purkaja.PurkuKoodi;
import TestiTiedostoLuokat.TestiKirjoittaja;
import TestiTiedostoLuokat.TestiLukija;
import Tietorakenteet.HuffmanKoodi;
import Tietorakenteet.OmaHashMap;
import Tietorakenteet.OmaList;
import Tietorakenteet.OmaMap;
import Tietorakenteet.TiedostoBlokki;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TiedostonPakkausJaPurkuTest {

    private TestiLukija lukija;
    private TestiKirjoittaja purettu;

    public TiedostonPakkausJaPurkuTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        lukija = new TestiLukija();
        lukija.tavut = "Kaikilla yliopiston opiskelijoilla on opinto-oikeus tietojenkäsittelytieteen perusopintoihin (25 op) ja aineopintokursseihin (35 op) tai menetelmätieteiden sivuainekokonaisuuteen kuuluviin tietojenkäsittelytieteen opintojaksoihin. Aineopintokursseja kannattaa pääsääntöisesti tehdä vasta, kun perusopinnot on suoritettu. Ellei kurssikohtaisesti toisin mainita, syventävien opintojen kursseille osallistuminen edellyttää aineopintokokonaisuuden suorittamista. Tietojenkäsittelytieteen sivuainekokonaisuuksien vaatimukset ovat  opinto-oppaassa ja tukintovaatimussivuilla pääainetutkintovaatimusten perässä".getBytes();
        purettu = new TestiKirjoittaja();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testaaEttaPuretunDatanKokoSamaKuinPakatunKunBlokkiKokoYksi() {
        try {
            pakkaaJaPura(1);
            assertEquals("Puretun datan koko eri kuin alkuperäisen!", lukija.koko(), purettu.koko());

        } catch (IOException ex) {
            assertTrue("Jotakin meni pieleen pakkauksessa tai purussa", false);
        }
    }

    @Test
    public void testaaEttaPurettuDataSamaKuinPakattuKunBlokkiKokoYksi() {
        try {
            pakkaaJaPura(1);
            byte[] puretutTavut = purettu.haeTavut();
            for (int i = 0; i < lukija.koko(); ++i) {
                assertEquals("Tavun arvo eri puretussa kuin alkuperäisessä!", lukija.tavut[i], puretutTavut[i]);
            }


        } catch (IOException ex) {
            assertTrue("Jotakin meni pieleen pakkauksessa tai purussa", false);
        }
    }

    @Test
    public void testaaEttaPuretunDatanKokoSamaKuinPakatunKunBlokkiKokoKaksi() {
        try {
            pakkaaJaPura(2);
            assertEquals("Puretun datan koko eri kuin alkuperäisen!", lukija.koko(), purettu.koko());

        } catch (IOException ex) {
            assertTrue("Jotakin meni pieleen pakkauksessa tai purussa", false);
        }
    }

    @Test
    public void testaaEttaPurettuDataSamaKuinPakattuKunBlokkiKokoKaksi() {
        try {
            pakkaaJaPura(2);
            byte[] puretutTavut = purettu.haeTavut();
            for (int i = 0; i < lukija.koko(); ++i) {
                assertEquals("Tavun arvo eri puretussa kuin alkuperäisessä!", lukija.tavut[i], puretutTavut[i]);
            }


        } catch (IOException ex) {
            assertTrue("Jotakin meni pieleen pakkauksessa tai purussa", false);
        }
    }

    // voi heittää IOException jos jotakin menee pieleen
    private void pakkaaJaPura(int blokkiKoko) throws IOException {
        KoodiMuodostaja koodiMuodostaja = new KoodiMuodostaja(blokkiKoko);
        Tiivistaja tiivistaja = new Tiivistaja(blokkiKoko);

        OmaMap<TiedostoBlokki, HuffmanKoodi> pakkausKoodit = koodiMuodostaja.muodostaKoodit(lukija);
        TestiKirjoittaja kirjoittaja = new TestiKirjoittaja();
        kirjoittaja.avaaTiedosto();
        int bittejaKaytetty = tiivistaja.tiivista(lukija, kirjoittaja, pakkausKoodit);

        assertTrue("Tiivistetty tiedosto suurempi kuin alkuperäinen!", kirjoittaja.koko() < lukija.koko());

        TestiLukija uusi = new TestiLukija();
        uusi.tavut = kirjoittaja.haeTavut();

        PurkuKoodi purkaja = new PurkuKoodi();
        purettu.avaaTiedosto();
        try {
            purkaja.kasitteleTiedosto(uusi, purettu, bittejaKaytetty, muodostaPurkuKoodit(pakkausKoodit));
        } catch (Exception ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    private OmaMap<HuffmanKoodi, byte[]> muodostaPurkuKoodit(OmaMap<TiedostoBlokki, HuffmanKoodi> pakkausKoodit) {
        OmaMap<HuffmanKoodi, byte[]> paluu = new OmaHashMap<HuffmanKoodi, byte[]>();

        OmaList<TiedostoBlokki> avaimet = pakkausKoodit.avaimet();
        for (int i = 0; i < avaimet.size(); ++i) {
            paluu.put(pakkausKoodit.get(avaimet.get(i)), avaimet.get(i).byteTaulukko);
        }
        return paluu;
    }
}
