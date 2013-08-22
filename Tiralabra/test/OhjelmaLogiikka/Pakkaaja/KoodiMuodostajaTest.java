package OhjelmaLogiikka.Pakkaaja;

import TestiTiedostoLuokat.TestiLukija;
import Tietorakenteet.TiedostoBlokki;
import Tietorakenteet.HuffmanKoodi;
import Tietorakenteet.OmaList;
import Tietorakenteet.OmaMap;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KoodiMuodostajaTest {

    private KoodiMuodostaja koodiMuodostaja;
    private TestiLukija lukija;

    public KoodiMuodostajaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        koodiMuodostaja = new KoodiMuodostaja(1);
        lukija = new TestiLukija();
        lukija.tavut = "ebbbccaaaa".getBytes();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testaaEttaOikeaMaaraKoodeja() {
        try {
            OmaMap<TiedostoBlokki, HuffmanKoodi> koodit = koodiMuodostaja.muodostaKoodit(lukija);
            assertEquals("Väärä määrä koodeja", 4, koodit.size());
        } catch (IOException ex) {
            assertTrue("IO-virhe - ei pitäisi heittää!: " + ex.getMessage(), false);
        }
    }

    @Test
    public void testaaEttaOikeaMaaraKoodeja2() {
        lukija.tavut = "abcdefghijklmnopqrstuvwxyz".getBytes();
        try {
            OmaMap<TiedostoBlokki, HuffmanKoodi> koodit = koodiMuodostaja.muodostaKoodit(lukija);

            assertEquals("Väärä määrä koodeja", lukija.koko(), koodit.size());
        } catch (IOException ex) {
            assertTrue("IO-virhe - ei pitäisi heittää!: " + ex.getMessage(), false);
        }
    }

    @Test
    public void testaaEttaOikeaMaaraKoodeja3() {
        lukija.tavut = "abcdefghijklmnopqrstuvwxyz".getBytes();
        try {
            koodiMuodostaja = new KoodiMuodostaja(4);
            OmaMap<TiedostoBlokki, HuffmanKoodi> koodit = koodiMuodostaja.muodostaKoodit(lukija);

            assertEquals("Väärä määrä koodeja", 7, koodit.size());
        } catch (IOException ex) {
            assertTrue("IO-virhe - ei pitäisi heittää!: " + ex.getMessage(), false);
        }
    }

    @Test
    public void testaaEttaKaikkiAvaimetEiNull() {
        try {

            OmaMap<TiedostoBlokki, HuffmanKoodi> koodit = koodiMuodostaja.muodostaKoodit(lukija);

            OmaList<TiedostoBlokki> avaimet = koodit.avaimet();
            for (int i = 0; i < avaimet.size(); ++i) {
                assertNotNull("Avain null!", avaimet.get(i));
            }

        } catch (IOException ex) {
            assertTrue("IO-virhe - ei pitäisi heittää!: " + ex.getMessage(), false);
        }
    }

    @Test
    public void testaaEttaKaikkiArvotEiNull() {
        try {
            OmaMap<TiedostoBlokki, HuffmanKoodi> koodit = koodiMuodostaja.muodostaKoodit(lukija);
            OmaList<TiedostoBlokki> avaimet = koodit.avaimet();
            for (int i = 0; i < avaimet.size(); ++i) {
                assertNotNull("Avain null!", koodit.get(avaimet.get(i)));
            }

        } catch (IOException ex) {
            assertTrue("IO-virhe - ei pitäisi heittää!: " + ex.getMessage(), false);
        }
    }

    @Test
    public void testaaEttaYleisinSaaLyhyimmanKoodin() {
        TiedostoBlokki a = new TiedostoBlokki();
        a.byteTaulukko = new byte[]{'a'};

        try {
            int lyhyinPituus = Integer.MAX_VALUE;
            OmaMap<TiedostoBlokki, HuffmanKoodi> koodit = koodiMuodostaja.muodostaKoodit(lukija);
            OmaList<TiedostoBlokki> avaimet = koodit.avaimet();
            for (int i = 0; i < avaimet.size(); ++i) {
                if (koodit.get(avaimet.get(i)).pituus < lyhyinPituus) {
                    lyhyinPituus = koodit.get(avaimet.get(i)).pituus;
                }
            }

            assertEquals("Yleisin koodi ei saanut lyhyintä avainta!", lyhyinPituus, koodit.get(a).pituus);

        } catch (IOException ex) {
            assertTrue("IO-virhe - ei pitäisi heittää!: " + ex.getMessage(), false);
        }
    }

    @Test
    public void testaaEttaHarvinaisinSaaPisimmanKoodin() {
        TiedostoBlokki e = new TiedostoBlokki();
        e.byteTaulukko = new byte[]{'e'};

        try {
            int pisinPituus = Integer.MIN_VALUE;
            OmaMap<TiedostoBlokki, HuffmanKoodi> koodit = koodiMuodostaja.muodostaKoodit(lukija);
            OmaList<TiedostoBlokki> avaimet = koodit.avaimet();
            for (int i = 0; i < avaimet.size(); ++i) {
                if (koodit.get(avaimet.get(i)).pituus > pisinPituus) {
                    pisinPituus = koodit.get(avaimet.get(i)).pituus;
                }
            }

            assertEquals("Harvinaisin koodi ei saanut pisintä avainta!", pisinPituus, koodit.get(e).pituus);

        } catch (IOException ex) {
            assertTrue("IO-virhe - ei pitäisi heittää!: " + ex.getMessage(), false);
        }
    }

    @Test
    public void testaaEttaKoodienKeskimaarainenPituusOnPienempiKuinAlkuperainen() {

        double alkuperainen = 8;
        double nykyinen = 0;
        lukija.tavut = "Tässä laissa säädettyä veroa ei suoriteta kruununperinnöstä eikä sellaisesta omaisuudesta, joka yhdistyksen sääntöjen mukaan sen toiminnan lakattua on siirtynyt toiselle, taikka joka testamentilla tai lahjana on annettu valtiolle tai sen laitokselle, maakunnalle, kunnalle, kuntayhtymälle, seurakunnalle tai muulle uskonnolliselle yhdyskunnalle sekä armeliaisuus- tai opetuslaitokselle. Sama olkoon voimassa myös omaisuudesta, joka on annettu aatteelliselle yhdistykselle tai muulle yhteisölle, laitokselle tai säätiölle, jolla, sen tarkoittamatta toiminnallaan tuottaa siihen osallisille taloudellisia etuja, on tieteellinen, taiteellinen, kansanvalistusta, maanpuolustusta tai kotimaista elinkeinoelämää edistävä tahi muu yleishyödyllinen tarkoitus.".getBytes();
        try {
            OmaMap<TiedostoBlokki, HuffmanKoodi> koodit = koodiMuodostaja.muodostaKoodit(lukija);
            OmaList<TiedostoBlokki> avaimet = koodit.avaimet();
            for (int i = 0; i < avaimet.size(); ++i) {
                nykyinen += koodit.get(avaimet.get(i)).pituus;
            }
            nykyinen /= (double) avaimet.size();
            assertTrue("Koodien keskimääräinen pituus pidempi kuin alkuperäisen!", nykyinen < alkuperainen);
        } catch (IOException ex) {
            assertTrue("IO-virhe - ei pitäisi heittää!: " + ex.getMessage(), false);
        }
    }
}
