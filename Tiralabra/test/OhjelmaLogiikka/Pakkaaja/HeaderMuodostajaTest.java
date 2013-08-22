/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OhjelmaLogiikka.Pakkaaja;

import TestiTiedostoLuokat.TestiKirjoittaja;
import TestiTiedostoLuokat.TestiLukija;
import Tietorakenteet.TiedostoBlokki;
import Tietorakenteet.HuffmanKoodi;
import Tietorakenteet.OmaArrayList;
import Tietorakenteet.OmaList;
import Tietorakenteet.Pari;
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
public class HeaderMuodostajaTest {

    private HeaderMuodostaja muodostaja;
    private TestiKirjoittaja kirjoittaja;
    OmaList<Pari<TiedostoBlokki, HuffmanKoodi>> koodit;

    public HeaderMuodostajaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        kirjoittaja = new TestiKirjoittaja();
        muodostaja = new HeaderMuodostaja();
        koodit = luoKoodit();
    }

    @After
    public void tearDown() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void heittaaPoikkeuksenJosMerkitsevienBittienMaaraLiianPieni() {
        try {
            koodit.get(0).toinen.pituus = 0;
            muodostaja.muodostaHeader(kirjoittaja, koodit, 0, 1);
        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void heittaaPoikkeuksenJosMerkitsevienBittienMaaraLiianSuuri() {
        try {
            koodit.get(0).toinen.pituus = 0;
            muodostaja.muodostaHeader(kirjoittaja, koodit, 9, 1);
        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void heittaaPoikkeuksenJosMerkitsevienBittienMaaraNegatiivinen() {
        try {
            koodit.get(0).toinen.pituus = 0;
            muodostaja.muodostaHeader(kirjoittaja, koodit, -5, 1);
        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void heittaaPoikkeuksenJosBlokkienPituusLiianSuuri() {
        try {
            koodit.get(0).toinen.pituus = 0;
            muodostaja.muodostaHeader(kirjoittaja, koodit, 5, 256);
        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void heittaaPoikkeuksenJosBlokkienPituusLiianPieni() {
        try {
            koodit.get(0).toinen.pituus = 0;
            muodostaja.muodostaHeader(kirjoittaja, koodit, 5, 0);
        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void heittaaPoikkeuksenJosBlokkienPituusNegatiivinen() {
        try {
            koodit.get(0).toinen.pituus = 0;
            muodostaja.muodostaHeader(kirjoittaja, koodit, 5, -1);
        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void heittaaPoikkeuksenJosKoodinPituusLiianSuuri() {
        try {
            koodit.get(0).toinen.pituus = 65;
            muodostaja.muodostaHeader(kirjoittaja, koodit, 5, 1);
        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void heittaaPoikkeuksenJosKoodinPituusLiianPieni() {
        try {
            koodit.get(0).toinen.pituus = 0;
            muodostaja.muodostaHeader(kirjoittaja, koodit, 5, 1);
        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void heittaaPoikkeuksenJosKoodinPituusNegatiivinen() {
        try {
            koodit.get(0).toinen.pituus = -5;
            muodostaja.muodostaHeader(kirjoittaja, koodit, 5, 1);
        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    @Test
    public void headerissaOikeaMerkitsevienBittienMaara() {
        try {
            muodostaja.muodostaHeader(kirjoittaja, koodit, 5, 1);

            assertEquals("Merkitsevien bittien määrä väärä headerissa", (5 - 128), kirjoittaja.haeTavut()[0]);
        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    @Test
    public void headerissaOikeaBlokkiKoko() {
        try {
            muodostaja.muodostaHeader(kirjoittaja, koodit, 5, 1);

            assertEquals("Blokkien koko väärä headerissa", (1 - 128), kirjoittaja.haeTavut()[1]);
        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }
    
    @Test
    public void headerPalauttaaOikeanHeaderinKoon() {
        try {
            long koko = muodostaja.muodostaHeader(kirjoittaja, koodit, 5, 1);

            assertEquals("Blokkien koko väärä headerissa", 12, koko);
        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }
    
    @Test
    public void headerissaKooditTallennettuOikein() {
        try {
            muodostaja.muodostaHeader(kirjoittaja, koodit, 5, 1);

            assertEquals("Koodin pituus A:lle väärä headerissa", (2 - 128), kirjoittaja.haeTavut()[2]);
            assertEquals("A:lle väärä blokki headerissa", 'a', kirjoittaja.haeTavut()[3]);

            assertEquals("Koodin pituus B:lle väärä headerissa", (2 - 128), kirjoittaja.haeTavut()[4]);
            assertEquals("B:lle väärä blokki headerissa", 'b', kirjoittaja.haeTavut()[5]);

            assertEquals("Koodin pituus C:lle väärä headerissa", (3 - 128), kirjoittaja.haeTavut()[6]);
            assertEquals("C:lle väärä blokki headerissa", 'c', kirjoittaja.haeTavut()[7]);

            assertEquals("Koodin pituus D:lle väärä headerissa", (3 - 128), kirjoittaja.haeTavut()[8]);
            assertEquals("D:lle väärä blokki headerissa", 'd', kirjoittaja.haeTavut()[9]);

            assertEquals("Koodin pituusE:lle väärä headerissa", (4 - 128), kirjoittaja.haeTavut()[10]);
            assertEquals("E:lle väärä blokki headerissa", 'e', kirjoittaja.haeTavut()[11]);

        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    @Test
    public void headerinPituusOikein() {
        try {
            muodostaja.muodostaHeader(kirjoittaja, koodit, 5, 1);
            assertEquals("headerin pituus väärä", 12, kirjoittaja.haeTavut().length);
            

        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    private OmaList<Pari<TiedostoBlokki, HuffmanKoodi>> luoKoodit() {

        Pari<TiedostoBlokki, HuffmanKoodi> pari;
        OmaList<Pari<TiedostoBlokki, HuffmanKoodi>> paluu = new OmaArrayList<Pari<TiedostoBlokki, HuffmanKoodi>>();

        TiedostoBlokki wrapper = new TiedostoBlokki();

        pari = new Pari<TiedostoBlokki, HuffmanKoodi>();
        HuffmanKoodi koodi = new HuffmanKoodi();
        koodi.pituus = 2;
        koodi.koodi = 0;
        wrapper.byteTaulukko = new byte[]{'a'};
        pari.ensimmainen = wrapper;
        pari.toinen = koodi;
        paluu.add(pari);

        pari = new Pari<TiedostoBlokki, HuffmanKoodi>();
        koodi = new HuffmanKoodi();
        wrapper = new TiedostoBlokki();
        koodi.pituus = 2;
        koodi.koodi = 1;
        wrapper.byteTaulukko = new byte[]{'b'};
        pari.ensimmainen = wrapper;
        pari.toinen = koodi;
        paluu.add(pari);

        pari = new Pari<TiedostoBlokki, HuffmanKoodi>();
        koodi = new HuffmanKoodi();
        wrapper = new TiedostoBlokki();
        koodi.pituus = 3;
        koodi.koodi = 7;
        wrapper.byteTaulukko = new byte[]{'c'};
        pari.ensimmainen = wrapper;
        pari.toinen = koodi;
        paluu.add(pari);

        pari = new Pari<TiedostoBlokki, HuffmanKoodi>();
        koodi = new HuffmanKoodi();
        wrapper = new TiedostoBlokki();
        koodi.pituus = 3;
        koodi.koodi = 5;
        wrapper.byteTaulukko = new byte[]{'d'};
        pari.ensimmainen = wrapper;
        pari.toinen = koodi;
        paluu.add(pari);

        pari = new Pari<TiedostoBlokki, HuffmanKoodi>();
        koodi = new HuffmanKoodi();
        wrapper = new TiedostoBlokki();
        koodi.pituus = 4;
        koodi.koodi = 15;
        wrapper.byteTaulukko = new byte[]{'e'};
        pari.ensimmainen = wrapper;
        pari.toinen = koodi;
        paluu.add(pari);

        return paluu;
    }
}
