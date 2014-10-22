package viidensuora.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import viidensuora.ai.Suunta;

public class RistinollaTest {

    private final Pelimerkki t = null;
    private final Pelimerkki x = Pelimerkki.RISTI;
    private final Pelimerkki o = Pelimerkki.NOLLA;

    private Ristinolla rn;
    private Ristinolla rn2;

    @Before
    public void setUp() {
        rn = new Ristinolla(1, 1, 1);
        Pelimerkki[][] pmArr = {
            {x, x, x, t, x},
            {t, x, t, t, t},
            {t, o, x, o, x},
            {t, t, o, o, x},
            {t, x, t, t, t}};
        rn2 = luoRistinolla(pmArr, 4);
    }

    private Ristinolla luoRistinolla(Pelimerkki[][] pmArr, int voittavaPituus) {
        int leveys = pmArr[0].length;
        int korkeus = pmArr.length;
        Ristinolla uusi = new Ristinolla(leveys, korkeus, voittavaPituus);
        for (int y1 = 0; y1 < korkeus; y1++) {
            for (int x1 = 0; x1 < leveys; x1++) {
                if (pmArr[y1][x1] != t) {
                    uusi.lisaaMerkki(x1, y1, pmArr[y1][x1]);
                }
            }
        }
        return uusi;
    }

    @Test
    public void laskeeVaakatasonRistitOikein() {
        assertEquals(3, rn2.perakkaisiaMerkkeja(0, 0, Suunta.OIKEA));
        assertEquals(3, rn2.perakkaisiaMerkkeja(2, 0, Suunta.VASEN));

        assertEquals(1, rn2.perakkaisiaMerkkeja(4, 0, Suunta.OIKEA));
        assertEquals(1, rn2.perakkaisiaMerkkeja(4, 0, Suunta.VASEN));

        assertEquals(1, rn2.perakkaisiaMerkkeja(2, 2, Suunta.OIKEA));
        assertEquals(1, rn2.perakkaisiaMerkkeja(2, 2, Suunta.VASEN));
    }
    
    
    @Test
    public void laskeeVaakatasonNollatOikein() {
        assertEquals(2, rn2.perakkaisiaMerkkeja(2, 3, Suunta.OIKEA));
        assertEquals(2, rn2.perakkaisiaMerkkeja(3, 3, Suunta.VASEN));
    }

    @Test
    public void laskeePystytasonRistitOikein() {
        assertEquals(2, rn2.perakkaisiaMerkkeja(1, 0, Suunta.ALAS));
        assertEquals(2, rn2.perakkaisiaMerkkeja(1, 1, Suunta.YLOS));

        assertEquals(1, rn2.perakkaisiaMerkkeja(4, 0, Suunta.ALAS));
        assertEquals(1, rn2.perakkaisiaMerkkeja(4, 0, Suunta.YLOS));

        assertEquals(2, rn2.perakkaisiaMerkkeja(4, 2, Suunta.ALAS));
        assertEquals(2, rn2.perakkaisiaMerkkeja(4, 3, Suunta.YLOS));
    }

    @Test
    public void laskeePystytasonNollatOikein() {
        assertEquals(2, rn2.perakkaisiaMerkkeja(3, 2, Suunta.ALAS));
        assertEquals(2, rn2.perakkaisiaMerkkeja(3, 3, Suunta.YLOS));
    }

    @Test
    public void laskeeDiag1RistitOikein() {
        assertEquals(3, rn2.perakkaisiaMerkkeja(0, 0, Suunta.ALAOIKEA));
        assertEquals(3, rn2.perakkaisiaMerkkeja(2, 2, Suunta.YLAVASEN));
    }

    @Test
    public void laskeeDiag2RistitOikein() {
        assertEquals(2, rn2.perakkaisiaMerkkeja(1, 1, Suunta.YLAOIKEA));
        assertEquals(2, rn2.perakkaisiaMerkkeja(2, 0, Suunta.ALAVASEN));
    }

    @Test
    public void laskeeDiag1NollatOikein() {
        assertEquals(2, rn2.perakkaisiaMerkkeja(1, 2, Suunta.ALAOIKEA));
        assertEquals(2, rn2.perakkaisiaMerkkeja(2, 3, Suunta.YLAVASEN));
    }

    @Test
    public void laskeeDiag2NollatOikein() {
        assertEquals(2, rn2.perakkaisiaMerkkeja(2, 3, Suunta.YLAOIKEA));
        assertEquals(2, rn2.perakkaisiaMerkkeja(3, 2, Suunta.ALAVASEN));
    }

    @Test
    public void laskeeTyhjatOikein() {
        assertEquals(3, rn2.perakkaisiaMerkkeja(2, 1, Suunta.OIKEA));
        assertEquals(3, rn2.perakkaisiaMerkkeja(4, 1, Suunta.VASEN));
    }

    @Test
    public void laskeePisimmatSuoratOikein() {
        assertEquals(3, rn2.pisinSuora(0, 0));
        assertEquals(3, rn2.pisinSuora(1, 1));
        assertEquals(2, rn2.pisinSuora(2, 3));
        assertEquals(1, rn2.pisinSuora(4, 0));
        assertEquals(2, rn2.pisinSuora(4, 2));
    }

    @Test
    public void tunnistaaVoiton() {
        Pelimerkki[][] pmArr = {
            {x, x, x},
            {t, t, t},
            {o, o, o}};
        Ristinolla rn3 = luoRistinolla(pmArr, 3);
        
        assertTrue(rn3.siirtoVoitti(0, 0));        
        assertTrue(rn3.siirtoVoitti(1, 0));
        assertTrue(rn3.siirtoVoitti(2, 0));
        
        assertTrue(!rn3.siirtoVoitti(0, 1));
        assertTrue(!rn3.siirtoVoitti(1, 1));
        assertTrue(!rn3.siirtoVoitti(2, 1));
        
        assertTrue(rn3.siirtoVoitti(0, 2));
        assertTrue(rn3.siirtoVoitti(1, 2));
        assertTrue(rn3.siirtoVoitti(2, 2));
    }

    @Test
    public void koordinaattiLaudalla() {
        assertTrue(rn.onLaudalla(0, 0));
    }

    @Test
    public void koordinaattiEiLaudalla() {
        assertTrue(!rn.onLaudalla(-1, -1));
        assertTrue(!rn.onLaudalla(0, -1));
        assertTrue(!rn.onLaudalla(1, -1));

        assertTrue(!rn.onLaudalla(-1, 0));
        assertTrue(!rn.onLaudalla(1, 0));

        assertTrue(!rn.onLaudalla(-1, 1));
        assertTrue(!rn.onLaudalla(0, 1));
        assertTrue(!rn.onLaudalla(1, 1));
    }

    @Test
    public void merkkiaEiLisataJosRuutuVarattu() {
        rn.lisaaNolla(0, 0);
        rn.lisaaRisti(0, 0);
        assertTrue(rn.onNolla(0, 0));
    }

    @Test
    public void nollanLisaysOnnistuu() {
        rn.lisaaNolla(0, 0);
        assertTrue(rn.onNolla(0, 0));
    }

    @Test
    public void ristinLisaysOnnistuu() {
        rn.lisaaRisti(0, 0);
        assertTrue(rn.onRisti(0, 0));
    }

    @Test
    public void merkinPoistoOnnistuu() {
        rn.lisaaRisti(0, 0);
        assertTrue(rn.onRisti(0, 0));
    }

    @Test
    public void tunnistaaTaydenLaudan() {
        rn.lisaaRisti(0, 0);
        assertTrue(rn.lautaTaynna());
    }

    @Test
    public void merkinLisaysKasvattaaMerkkilaskuria() {
        rn.lisaaRisti(0, 0);
        assertEquals(1, rn.laudallaMerkkeja());
    }

    @Test
    public void merkinPoistoVahentaaMerkkilaskuria() {
        rn.lisaaRisti(0, 0);
        assertEquals(1, rn.laudallaMerkkeja());
        rn.poistaMerkki(0, 0);
        assertEquals(0, rn.laudallaMerkkeja());
    }

    @Test
    public void palauttaaMerkinOikein() {
        rn.lisaaRisti(0, 0);
        assertTrue(rn.getMerkki(0, 0) == Pelimerkki.RISTI);
    }

    @Test
    public void palauttaaTyhjanMerkinOikein() {
        assertTrue(rn.getMerkki(0, 0) == null);
    }

    @Test
    public void laudanKokoOikein() {
        assertEquals(1, rn.korkeus);
        assertEquals(1, rn.leveys);
    }

    @Test
    public void tyhjaRuutuTunnistuu() {
        assertTrue(rn.ruutuOnTyhja(0, 0));
        rn.lisaaRisti(0, 0);
        assertTrue(!rn.ruutuOnTyhja(0, 0));
    }

    @Test
    public void vapaatRuudutOikein() {
        assertEquals(1, rn.vapaitaRuutuja());
        rn.lisaaNolla(0, 0);
        assertEquals(0, rn.vapaitaRuutuja());
    }

}
