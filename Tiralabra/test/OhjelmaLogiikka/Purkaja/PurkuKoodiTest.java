/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OhjelmaLogiikka.Purkaja;

import Poikkeukset.PurkuException;
import TestiTiedostoLuokat.TestiKirjoittaja;
import TestiTiedostoLuokat.TestiLukija;
import Tietorakenteet.HuffmanKoodi;
import Tietorakenteet.OmaHashMap;
import Tietorakenteet.OmaMap;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Omistaja
 */
public class PurkuKoodiTest {

    private PurkuKoodi purkaja;
    private TestiKirjoittaja kirjoittaja;
    private TestiLukija lukija;
    private OmaMap<HuffmanKoodi, byte[]> koodit;
    private int viimeisessaTavussaMerkitseviaBitteja;

    public PurkuKoodiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            purkaja = new PurkuKoodi();
            kirjoittaja = new TestiKirjoittaja();
            lukija = new TestiLukija();

            // pakattuna teksti: abcadeabed - 8 tavun rypäissä koodit kirjaimille: abca | dea | bed
            // tallennettu tavuun väärinpäin ->  acba - aed - deb
            // kirjainten koodit tavuihin:
            // ensimmäisen tavun koodi: 00|01|10|00 = 24
            // toisen tavun koodi: 00|111|011= 59
            // kolmannen tavun koodi 011|111|10 = 126
            lukija.tavut = new byte[]{24, 59, 126};

            koodit = new OmaHashMap<HuffmanKoodi, byte[]>();
            viimeisessaTavussaMerkitseviaBitteja = 8;
            luoKoodit();
            kirjoittaja.avaaTiedosto();
            lukija.avaaTiedosto();
        } catch (FileNotFoundException ex) {
            // Ei pitäisi ikinä tapahtua, Testiobjektit eivät heitä poikkeuksia
        }
    }

    @After
    public void tearDown() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void heittaaIllegalArgumentExceptionJosMerkitsevienBittienMaaraLiianPieni() {
        try {
            purkaja.kasitteleTiedosto(lukija, kirjoittaja, 0, koodit);

        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        } catch (PurkuException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void heittaaIllegalArgumentExceptionJosMerkitsevienBittienMaaraNegatiivinen() {
        try {
            purkaja.kasitteleTiedosto(lukija, kirjoittaja, -1, koodit);

        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        } catch (PurkuException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    @Test(expected = PurkuException.class)
    public void heittaaPurkuExceptionJosSopivaaKoodiaEiLoydy() throws PurkuException {
        try {
            lukija.tavut = "Tänne paljon roskatavuja että varmasti tulee poikkeus koska sopivaa koodia ei vain löydy".getBytes();
            koodit.clear();
            purkaja.kasitteleTiedosto(lukija, kirjoittaja, 1, koodit);

        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
        System.out.println(new String(kirjoittaja.haeTavut()));
    }

    @Test(expected = PurkuException.class)
    public void heittaaPurkuExceptionJosKasittelemattomiaKoodejaLopussa() throws PurkuException {
        try {
            purkaja.kasitteleTiedosto(lukija, kirjoittaja, 7 , koodit);

        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
        System.out.println(new String(kirjoittaja.haeTavut()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void heittaaIllegalArgumentExceptionJosMerkitsevienBittienMaaraLiianSuuri() {
        try {
            purkaja.kasitteleTiedosto(lukija, kirjoittaja, 9, koodit);

        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        } catch (PurkuException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    @Test
    public void purkaaOikein() {
        try {
            purkaja.kasitteleTiedosto(lukija, kirjoittaja, viimeisessaTavussaMerkitseviaBitteja, koodit);

            String teksti = new String(kirjoittaja.haeTavut());
            assertEquals("Purkaja purki väärin: ", "abcadeabed", teksti);
        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        } catch (PurkuException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    private void luoKoodit() {

        HuffmanKoodi koodi;


        koodi = new HuffmanKoodi();

        koodi.pituus = 2;
        koodi.koodi = 0;
        koodit.put(koodi, new byte[]{'a'});


        koodi = new HuffmanKoodi();
        koodi.pituus = 2;
        koodi.koodi = 2;
        koodit.put(koodi, new byte[]{'b'});


        koodi = new HuffmanKoodi();
        koodi.pituus = 2;
        koodi.koodi = 1;
        koodit.put(koodi, new byte[]{'c'});


        koodi = new HuffmanKoodi();
        koodi.pituus = 3;
        koodi.koodi = 3;
        koodit.put(koodi, new byte[]{'d'});


        koodi = new HuffmanKoodi();
        koodi.pituus = 3;
        koodi.koodi = 7;
        koodit.put(koodi, new byte[]{'e'});
    }
}
