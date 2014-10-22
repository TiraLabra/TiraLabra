package viidensuora.ai;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import viidensuora.logiikka.Pelimerkki;
import viidensuora.logiikka.Ristinolla;

/**
 *
 * @author juha
 */
public class SuorituskykyTest {

    private final Pelimerkki t = null;
    private final Pelimerkki x = Pelimerkki.RISTI;
    private final Pelimerkki o = Pelimerkki.NOLLA;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void koko3x3() {
        Pelimerkki[][] pmArr = {
            {t, t, t},
            {t, t, t},
            {t, t, t}};

        Pelimerkki[][] pmArr2 = {
            {t, t, t},
            {t, t, t},
            {t, t, t}};

        Ristinolla rnMM = luoRistinolla(pmArr, 3);
        Ristinolla rnAB = luoRistinolla(pmArr2, 3);

        MinMax minmax = new MinMax(rnMM, new MunEvaluoija());
        AlphaBetaKarsinta abKarsinta = new AlphaBetaKarsinta(rnAB, new MunEvaluoija());

        Hakutulos tulosMM = minmax.etsiRistinSiirto(9);
        Hakutulos tulosAB = abKarsinta.etsiRistinSiirto(9);
        System.out.println("3x3 Suorituskykytesti:");
        System.out.println("MinMax: " + tulosMM.evaluoitujaTilanteita + " (" + tulosMM.hakuaika / 1000.0 + "s)");
        System.out.println("AlpBet: " + tulosAB.evaluoitujaTilanteita + " (" + tulosAB.hakuaika / 1000.0 + "s)");
        System.out.println("");
        assertTrue(tulosMM.evaluoitujaTilanteita > tulosAB.evaluoitujaTilanteita);
        assertTrue(tulosMM.siirronArvo == tulosAB.siirronArvo);
    }

    @Test
    public void koko4x4() {
        Pelimerkki[][] pmArr = {
            {t, t, t, t},
            {t, t, t, t},
            {t, t, t, t},
            {t, t, t, t}};

        Pelimerkki[][] pmArr2 = {
            {t, t, t, t},
            {t, t, t, t},
            {t, t, t, t},
            {t, t, t, t}};

        Ristinolla rnMM = luoRistinolla(pmArr, 4);
        Ristinolla rnAB = luoRistinolla(pmArr2, 4);

        MinMax minmax = new MinMax(rnMM, new MunEvaluoija());
        AlphaBetaKarsinta abKarsinta = new AlphaBetaKarsinta(rnAB, new MunEvaluoija());

        Hakutulos tulosMM = minmax.etsiRistinSiirto(5);
        Hakutulos tulosAB = abKarsinta.etsiRistinSiirto(5);
        System.out.println("4x4 Suorituskykytesti:");
        System.out.println("MinMax: " + tulosMM.evaluoitujaTilanteita + " (" + tulosMM.hakuaika / 1000.0 + "s)");
        System.out.println("AlpBet: " + tulosAB.evaluoitujaTilanteita + " (" + tulosAB.hakuaika / 1000.0 + "s)");
        System.out.println("");
        assertTrue(tulosMM.evaluoitujaTilanteita > tulosAB.evaluoitujaTilanteita);
        assertTrue(tulosMM.siirronArvo == tulosAB.siirronArvo);
    }

    @Test
    public void koko8x8() {
        Pelimerkki[][] pmArr = {
            {t, t, t, t, t},
            {t, t, t, t, t},
            {t, t, t, t, t},
            {t, t, t, t, t},
            {t, t, t, t, t}};

        Pelimerkki[][] pmArr2 = {
            {t, t, t, t, t},
            {t, t, t, t, t},
            {t, t, t, t, t},
            {t, t, t, t, t},
            {t, t, t, t, t}};

        Ristinolla rnMM = luoRistinolla(pmArr, 5);
        Ristinolla rnAB = luoRistinolla(pmArr2, 5);

        MinMax minmax = new MinMax(rnMM, new MunEvaluoija());
        AlphaBetaKarsinta abKarsinta = new AlphaBetaKarsinta(rnAB, new MunEvaluoija());

        Hakutulos tulosMM = minmax.etsiRistinSiirto(5);
        Hakutulos tulosAB = abKarsinta.etsiRistinSiirto(5);
        System.out.println("5x5 Suorituskykytesti:");
        System.out.println("MinMax: " + tulosMM.evaluoitujaTilanteita + " (" + tulosMM.hakuaika / 1000.0 + "s)");
        System.out.println("AlpBet: " + tulosAB.evaluoitujaTilanteita + " (" + tulosAB.hakuaika / 1000.0 + "s)");
        System.out.println("");
        assertTrue(tulosMM.evaluoitujaTilanteita > tulosAB.evaluoitujaTilanteita);
        assertTrue(tulosMM.siirronArvo == tulosAB.siirronArvo);
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
}
