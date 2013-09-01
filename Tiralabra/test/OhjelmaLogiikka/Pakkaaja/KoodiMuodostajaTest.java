package OhjelmaLogiikka.Pakkaaja;

import TestiTiedostoLuokat.TestiLukija;
import Tietorakenteet.HuffmanKoodi;
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

public class KoodiMuodostajaTest {

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
        lukija = new TestiLukija();
        lukija.tavut = "ebbbccaaaa".getBytes();

    }

    @After
    public void tearDown() {
    }

    private void maaraTestiMetodi(KoodiMuodostaja mudostaja, TestiLukija teksti, long oletettu) {
        try {
            OmaMap<TiedostoBlokki, HuffmanKoodi> koodit = mudostaja.muodostaKoodit(teksti);
            assertEquals("Väärä määrä koodeja", oletettu, koodit.size());
        } catch (IOException ex) {
            assertTrue("IO-virhe - ei pitäisi heittää!: " + ex.getMessage(), false);
        }
    }

    @Test
    public void testaaEttaOikeaMaaraKoodeja() {
        maaraTestiMetodi(new KoodiMuodostaja(1), lukija, 4);
    }

    @Test
    public void testaaEttaOikeaMaaraKoodejaKunBlokkiKoko2() {
        maaraTestiMetodi(new KoodiMuodostaja(2), lukija, 4);
    }

    @Test
    public void testaaEttaOikeaMaaraKoodejaEriTeksti() {
        lukija.tavut = "abcdefghijklmnopqrstuvwxyz".getBytes();
        maaraTestiMetodi(new KoodiMuodostaja(1), lukija, lukija.koko());
    }

    @Test
    public void testaaEttaOikeaMaaraKoodejaEriTekstiKunBlokkiKoko2() {
        lukija.tavut = "abcdefghijklmnopqrstuvwxyz".getBytes();
        maaraTestiMetodi(new KoodiMuodostaja(2), lukija, 13);
    }

    @Test
    public void testaaEttaOikeaMaaraKoodejaEriTekstiKunBlokkiKoko4() {
        lukija.tavut = "abcdefghijklmnopqrstuvwxyz".getBytes();
        maaraTestiMetodi(new KoodiMuodostaja(4), lukija, 7);
    }

    private void nullAvainTestiMetodi(KoodiMuodostaja muodostaja) {
        try {

            OmaMap<TiedostoBlokki, HuffmanKoodi> koodit = muodostaja.muodostaKoodit(lukija);
            OmaList<TiedostoBlokki> avaimet = koodit.avaimet();
            for (int i = 0; i < avaimet.size(); ++i) {
                assertNotNull("Avain null!", avaimet.get(i));
            }

        } catch (IOException ex) {
            assertTrue("IO-virhe - ei pitäisi heittää!: " + ex.getMessage(), false);
        }
    }

    @Test
    public void testaaEttaKaikkiAvaimetEiNull() {
        nullAvainTestiMetodi(new KoodiMuodostaja(1));
    }

    @Test
    public void testaaEttaKaikkiAvaimetEiNullKunBlokkiKoko2() {
        nullAvainTestiMetodi(new KoodiMuodostaja(2));
    }

    private void nullArvoTestiMetodi(KoodiMuodostaja muodostaja) {
        try {
            OmaMap<TiedostoBlokki, HuffmanKoodi> koodit = muodostaja.muodostaKoodit(lukija);
            OmaList<TiedostoBlokki> avaimet = koodit.avaimet();
            for (int i = 0; i < avaimet.size(); ++i) {
                assertNotNull("Avain null!", koodit.get(avaimet.get(i)));
            }

        } catch (IOException ex) {
            assertTrue("IO-virhe - ei pitäisi heittää!: " + ex.getMessage(), false);
        }
    }

    @Test
    public void testaaEttaKaikkiArvotEiNull() {
        nullArvoTestiMetodi(new KoodiMuodostaja(1));
    }

    @Test
    public void testaaEttaKaikkiArvotEiNullKunBlokkiKoko2() {
        nullArvoTestiMetodi(new KoodiMuodostaja(2));
    }

    private void lyhyinKoodiTestiMetodi(KoodiMuodostaja muodostaja, TiedostoBlokki tiedostoBlokki) {

        try {
            int lyhyinPituus = Integer.MAX_VALUE;
            OmaMap<TiedostoBlokki, HuffmanKoodi> koodit = muodostaja.muodostaKoodit(lukija);
            OmaList<TiedostoBlokki> avaimet = koodit.avaimet();
            for (int i = 0; i < avaimet.size(); ++i) {
                if (koodit.get(avaimet.get(i)).pituus < lyhyinPituus) {
                    lyhyinPituus = koodit.get(avaimet.get(i)).pituus;
                }
            }

            assertEquals("Yleisin koodi ei saanut lyhyintä avainta!", lyhyinPituus, koodit.get(tiedostoBlokki).pituus);

        } catch (IOException ex) {
            assertTrue("IO-virhe - ei pitäisi heittää!: " + ex.getMessage(), false);
        }
    }

    @Test
    public void testaaEttaYleisinSaaLyhyimmanKoodin() {
        TiedostoBlokki blokki = new TiedostoBlokki();
        blokki.byteTaulukko = "a".getBytes();
        lyhyinKoodiTestiMetodi(new KoodiMuodostaja(1), blokki);
    }

    @Test
    public void testaaEttaYleisinSaaLyhyimmanKoodinKunBlokkiKoko2() {
        TiedostoBlokki blokki = new TiedostoBlokki();
        blokki.byteTaulukko = "aa".getBytes();
        lyhyinKoodiTestiMetodi(new KoodiMuodostaja(2), blokki);
    }

    private void pisinKoodiTestiMetodi(KoodiMuodostaja muodostaja, TiedostoBlokki blokki) {
        try {
            int pisinPituus = Integer.MIN_VALUE;
            OmaMap<TiedostoBlokki, HuffmanKoodi> koodit = muodostaja.muodostaKoodit(lukija);
            OmaList<TiedostoBlokki> avaimet = koodit.avaimet();
            for (int i = 0; i < avaimet.size(); ++i) {
                if (koodit.get(avaimet.get(i)).pituus > pisinPituus) {
                    pisinPituus = koodit.get(avaimet.get(i)).pituus;
                }
            }

            assertEquals("Harvinaisin koodi ei saanut pisintä avainta!", pisinPituus, koodit.get(blokki).pituus);

        } catch (IOException ex) {
            assertTrue("IO-virhe - ei pitäisi heittää!: " + ex.getMessage(), false);
        }
    }

    @Test
    public void testaaEttaHarvinaisinSaaPisimmanKoodin() {
        TiedostoBlokki blokki = new TiedostoBlokki();
        blokki.byteTaulukko = "e".getBytes();
        pisinKoodiTestiMetodi(new KoodiMuodostaja(1), blokki);
    }

    @Test
    public void testaaEttaHarvinaisinSaaPisimmanKoodinKunBlokkiKoko2() {
        TiedostoBlokki blokki = new TiedostoBlokki();
        blokki.byteTaulukko = "eb".getBytes();
        pisinKoodiTestiMetodi(new KoodiMuodostaja(2), blokki);
    }

    private void keskimaarainenPituusTestiMetodi(KoodiMuodostaja muodostaja, double alkuPerainenPituus) {
        double nykyinen = 0;
        lukija.tavut = "Tässä laissa säädettyä veroa ei suoriteta kruununperinnöstä eikä sellaisesta omaisuudesta, joka yhdistyksen sääntöjen mukaan sen toiminnan lakattua on siirtynyt toiselle, taikka joka testamentilla tai lahjana on annettu valtiolle tai sen laitokselle, maakunnalle, kunnalle, kuntayhtymälle, seurakunnalle tai muulle uskonnolliselle yhdyskunnalle sekä armeliaisuus- tai opetuslaitokselle. Sama olkoon voimassa myös omaisuudesta, joka on annettu aatteelliselle yhdistykselle tai muulle yhteisölle, laitokselle tai säätiölle, jolla, sen tarkoittamatta toiminnallaan tuottaa siihen osallisille taloudellisia etuja, on tieteellinen, taiteellinen, kansanvalistusta, maanpuolustusta tai kotimaista elinkeinoelämää edistävä tahi muu yleishyödyllinen tarkoitus.".getBytes();
        try {
            OmaMap<TiedostoBlokki, HuffmanKoodi> koodit = muodostaja.muodostaKoodit(lukija);
            OmaList<TiedostoBlokki> avaimet = koodit.avaimet();
            for (int i = 0; i < avaimet.size(); ++i) {
                nykyinen += koodit.get(avaimet.get(i)).pituus;
            }
            nykyinen /= (double) avaimet.size();
            assertTrue("Koodien keskimääräinen pituus pidempi kuin alkuperäisen!", nykyinen < alkuPerainenPituus);
        } catch (IOException ex) {
            assertTrue("IO-virhe - ei pitäisi heittää!: " + ex.getMessage(), false);
        }
    }

    @Test
    public void testaaEttaKoodienKeskimaarainenPituusOnPienempiKuinAlkuperainen() {
        keskimaarainenPituusTestiMetodi(new KoodiMuodostaja(1), 8);
    }

    @Test
    public void testaaEttaKoodienKeskimaarainenPituusOnPienempiKuinAlkuperainenKunBlokinKoko2() {
        keskimaarainenPituusTestiMetodi(new KoodiMuodostaja(2), 16);
    }

    @Test
    public void blokitSaavatOikeatKoodit() {
        KoodiMuodostaja muodostaja = new KoodiMuodostaja(1);

        try {
            // pitäisi tulla: a - 0 - b - 01 - c 111 - e 011
            OmaMap<TiedostoBlokki, HuffmanKoodi> koodit = muodostaja.muodostaKoodit(lukija);
            TiedostoBlokki blokki = new TiedostoBlokki();

            blokki.byteTaulukko = "a".getBytes();
            assertEquals("Väärä huffman-koodi blokille!", 0, koodit.get(blokki).koodi);

            blokki.byteTaulukko = "b".getBytes();
            assertEquals("Väärä huffman-koodi blokille!", 1, koodit.get(blokki).koodi);

            blokki.byteTaulukko = "c".getBytes();
            assertEquals("Väärä huffman-koodi blokille!", 7, koodit.get(blokki).koodi);

            blokki.byteTaulukko = "e".getBytes();
            assertEquals("Väärä huffman-koodi blokille!", 3, koodit.get(blokki).koodi);

        } catch (IOException ex) {
            assertTrue("IO-virhe - ei pitäisi heittää!: " + ex.getMessage(), false);
        }
    }

    @Test
    public void blokitSaavatOikeatKoodit2() {
        KoodiMuodostaja muodostaja = new KoodiMuodostaja(2);

        try {
            // pitäisi tulla: aa - 0 - bb - 01  - cc 011 - eb 111
            OmaMap<TiedostoBlokki, HuffmanKoodi> koodit = muodostaja.muodostaKoodit(lukija);
            TiedostoBlokki blokki = new TiedostoBlokki();

            blokki.byteTaulukko = "aa".getBytes();
            assertEquals("Väärä huffman-koodi blokille!", 0, koodit.get(blokki).koodi);

            blokki.byteTaulukko = "bb".getBytes();
            assertEquals("Väärä huffman-koodi blokille!", 1, koodit.get(blokki).koodi);

            blokki.byteTaulukko = "cc".getBytes();
            assertEquals("Väärä huffman-koodi blokille!", 3, koodit.get(blokki).koodi);

            blokki.byteTaulukko = "eb".getBytes();
            assertEquals("Väärä huffman-koodi blokille!", 7, koodit.get(blokki).koodi);

        } catch (IOException ex) {
            assertTrue("IO-virhe - ei pitäisi heittää!: " + ex.getMessage(), false);
        }
    }

    @Test
    public void blokeillaOikeatPituudet() {
        KoodiMuodostaja muodostaja = new KoodiMuodostaja(1);
        try {
            // pitäisi tulla: a 1 - b 2  - c 3 - e 3
            OmaMap<TiedostoBlokki, HuffmanKoodi> koodit = muodostaja.muodostaKoodit(lukija);
            TiedostoBlokki blokki = new TiedostoBlokki();

            blokki.byteTaulukko = "a".getBytes();
            assertEquals("Väärä huffman-koodi blokille!", 1, koodit.get(blokki).pituus);

            blokki.byteTaulukko = "b".getBytes();
            assertEquals("Väärä huffman-koodi blokille!", 2, koodit.get(blokki).pituus);

            blokki.byteTaulukko = "c".getBytes();
            assertEquals("Väärä huffman-koodi blokille!", 3, koodit.get(blokki).pituus);

            blokki.byteTaulukko = "e".getBytes();
            assertEquals("Väärä huffman-koodi blokille!", 3, koodit.get(blokki).pituus);

        } catch (IOException ex) {
            assertTrue("IO-virhe - ei pitäisi heittää!: " + ex.getMessage(), false);
        }
    }

    @Test
    public void blokeillaOikeatPituudetKunBlokinKoko2() {
        KoodiMuodostaja muodostaja = new KoodiMuodostaja(2);
        try {
            // pitäisi tulla: a 1 - b 2  - c 3 - e 3
            OmaMap<TiedostoBlokki, HuffmanKoodi> koodit = muodostaja.muodostaKoodit(lukija);
            TiedostoBlokki blokki = new TiedostoBlokki();

            blokki.byteTaulukko = "aa".getBytes();
            assertEquals("Väärä huffman-koodi blokille!", 1, koodit.get(blokki).pituus);

            blokki.byteTaulukko = "bb".getBytes();
            assertEquals("Väärä huffman-koodi blokille!", 2, koodit.get(blokki).pituus);

            blokki.byteTaulukko = "cc".getBytes();
            assertEquals("Väärä huffman-koodi blokille!", 3, koodit.get(blokki).pituus);

            blokki.byteTaulukko = "eb".getBytes();
            assertEquals("Väärä huffman-koodi blokille!", 3, koodit.get(blokki).pituus);

        } catch (IOException ex) {
            assertTrue("IO-virhe - ei pitäisi heittää!: " + ex.getMessage(), false);
        }
    }
}
