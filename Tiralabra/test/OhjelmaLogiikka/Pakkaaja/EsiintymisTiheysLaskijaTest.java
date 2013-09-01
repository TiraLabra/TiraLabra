/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OhjelmaLogiikka.Pakkaaja;

import TestiTiedostoLuokat.TestiLukija;
import Tietorakenteet.OmaMap;
import Tietorakenteet.TiedostoBlokki;
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
public class EsiintymisTiheysLaskijaTest {

    private EsiintymisTiheysLaskija laskija;
    private TestiLukija lukija;

    public EsiintymisTiheysLaskijaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        laskija = new EsiintymisTiheysLaskija();
        lukija = new TestiLukija();
        lukija.tavut = "aaaabbbccdefg".getBytes();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void laskeeEsiintymisTiheydetOikeinKunBlokkiKoko1() {
        try {
            OmaMap<TiedostoBlokki, Integer> esiintymisTiheydet = laskija.laskeEsiintymisTiheydet(lukija, 1);
            TiedostoBlokki blokki = new TiedostoBlokki();

            blokki.byteTaulukko = "a".getBytes();
            assertEquals("Esiintymistiheys laskettu väärin", new Integer(4), esiintymisTiheydet.get(blokki));

            blokki.byteTaulukko = "b".getBytes();
            assertEquals("Esiintymistiheys laskettu väärin", new Integer(3), esiintymisTiheydet.get(blokki));

            blokki.byteTaulukko = "c".getBytes();
            assertEquals("Esiintymistiheys laskettu väärin", new Integer(2), esiintymisTiheydet.get(blokki));

            blokki.byteTaulukko = "d".getBytes();
            assertEquals("Esiintymistiheys laskettu väärin", new Integer(1), esiintymisTiheydet.get(blokki));


        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    @Test
    public void laskeeEsiintymisTiheydetOikeinKunBlokkiKoko2() {
        try {
            OmaMap<TiedostoBlokki, Integer> esiintymisTiheydet = laskija.laskeEsiintymisTiheydet(lukija, 2);
            TiedostoBlokki blokki = new TiedostoBlokki();

            blokki.byteTaulukko = "aa".getBytes();
            assertEquals("Esiintymistiheys laskettu väärin", new Integer(2), esiintymisTiheydet.get(blokki));

            blokki.byteTaulukko = "bb".getBytes();
            assertEquals("Esiintymistiheys laskettu väärin", new Integer(1), esiintymisTiheydet.get(blokki));

            blokki.byteTaulukko = "bc".getBytes();
            assertEquals("Esiintymistiheys laskettu väärin", new Integer(1), esiintymisTiheydet.get(blokki));

            blokki.byteTaulukko = "cd".getBytes();
            assertEquals("Esiintymistiheys laskettu väärin", new Integer(1), esiintymisTiheydet.get(blokki));

            blokki.byteTaulukko = "ef".getBytes();
            assertEquals("Esiintymistiheys laskettu väärin", new Integer(1), esiintymisTiheydet.get(blokki));

            blokki.byteTaulukko = "g".getBytes();
            assertEquals("Esiintymistiheys laskettu väärin", new Integer(1), esiintymisTiheydet.get(blokki));



        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }

    @Test
    public void laskeeEsiintymisTiheydetOikeinKunBlokkiKoko3() {
        try {
            OmaMap<TiedostoBlokki, Integer> esiintymisTiheydet = laskija.laskeEsiintymisTiheydet(lukija, 3);
            TiedostoBlokki blokki = new TiedostoBlokki();

            blokki.byteTaulukko = "aaa".getBytes();
            assertEquals("Esiintymistiheys laskettu väärin", new Integer(1), esiintymisTiheydet.get(blokki));

            blokki.byteTaulukko = "abb".getBytes();
            assertEquals("Esiintymistiheys laskettu väärin", new Integer(1), esiintymisTiheydet.get(blokki));

            blokki.byteTaulukko = "bcc".getBytes();
            assertEquals("Esiintymistiheys laskettu väärin", new Integer(1), esiintymisTiheydet.get(blokki));

            blokki.byteTaulukko = "def".getBytes();
            assertEquals("Esiintymistiheys laskettu väärin", new Integer(1), esiintymisTiheydet.get(blokki));

            blokki.byteTaulukko = "g".getBytes();
            assertEquals("Esiintymistiheys laskettu väärin", new Integer(1), esiintymisTiheydet.get(blokki));

        } catch (IOException ex) {
            assertTrue("Ei pitäisi tapahtua...", false);
        }
    }
}
