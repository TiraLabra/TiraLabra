package viidensuora.ai;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import viidensuora.logiikka.Pelimerkki;
import viidensuora.logiikka.Ristinolla;

public class AlphaBetaKarsintaTest {

    private final Pelimerkki t = null;
    private final Pelimerkki x = Pelimerkki.RISTI;
    private final Pelimerkki o = Pelimerkki.NOLLA;

    private AlphaBetaKarsinta abKarsinta;

    @Before
    public void setUp() {
    }

    @Test
    public void tunnistaaYhdenSiirronPaassaOlevanVoiton() {
        Pelimerkki[][] pmArr = {
            {x, o, o},
            {t, x, x},
            {o, t, t}};
        Ristinolla rn = luoRistinolla(pmArr, 3);
        abKarsinta = new AlphaBetaKarsinta(rn, new MunEvaluoija());
        Hakutulos tulos = abKarsinta.etsiRistinSiirto(99);
        assertTrue((tulos.parasSiirto.x == 0 && tulos.parasSiirto.y == 1)
                || (tulos.parasSiirto.x == 2 && tulos.parasSiirto.y == 2));
    }

    @Test
    public void estaaVastustajanVarmanVoiton() {
        Pelimerkki[][] pmArr = {
            {t, t, t, t, t, o, t, t, t},
            {t, t, t, t, t, t, t, t, t},
            {t, t, t, x, x, x, t, t, t},
            {t, t, t, t, t, t, t, t, t},
            {t, t, t, t, t, t, t, o, t}};
        Ristinolla rn = luoRistinolla(pmArr, 5);
        abKarsinta = new AlphaBetaKarsinta(rn, new MunEvaluoija());
        Hakutulos tulos = abKarsinta.etsiNollanSiirto(4);
        assertTrue((tulos.parasSiirto.x == 2 && tulos.parasSiirto.y == 2)
                || (tulos.parasSiirto.x == 6 && tulos.parasSiirto.y == 2));
    }

    @Test
    public void valitseePisimmanSuoranEriVaihtoehdoista() {
        Pelimerkki[][] pmArr = {
            {t, t, t, x, x, t, t, t, t},
            {t, t, t, t, t, t, t, t, t},
            {t, t, t, x, x, x, t, t, t},
            {t, t, t, t, t, t, t, t, t},
            {t, t, t, x, x, t, t, t, t}};
        Ristinolla rn = luoRistinolla(pmArr, 5);
        abKarsinta = new AlphaBetaKarsinta(rn, new MunEvaluoija());
        Hakutulos tulos = abKarsinta.etsiRistinSiirto(4);
        assertTrue((tulos.parasSiirto.x == 2 && tulos.parasSiirto.y == 2)
                || (tulos.parasSiirto.x == 6 && tulos.parasSiirto.y == 2));
    }

    @Test
    public void eiKasvataTurhaanPitkaaSuoraaJostaEiVoiSyntyaVoittavaa() {
        Pelimerkki[][] pmArr = {
            {t, t, t, t, t, t, t, t, t},
            {t, t, t, t, t, t, t, t, t},
            {t, t, t, t, o, x, x, x, t},
            {t, t, t, t, t, t, t, t, t},
            {t, t, t, t, t, t, t, t, t}};
        Ristinolla rn = luoRistinolla(pmArr, 5);
        abKarsinta = new AlphaBetaKarsinta(rn, new MunEvaluoija());
        Hakutulos tulos = abKarsinta.etsiRistinSiirto(4);
        assertTrue(!(tulos.parasSiirto.x == 8 && tulos.parasSiirto.y == 2));
    }

    @Test
    public void laskeeTyhjan3x3pelinTaydellisenArvonOikein() {
        Pelimerkki[][] pmArr = {
            {t, t, t},
            {t, t, t},
            {t, t, t}};
        Ristinolla rn = luoRistinolla(pmArr, 3);
        abKarsinta = new AlphaBetaKarsinta(rn, new MunEvaluoija());
        Hakutulos tulos = abKarsinta.etsiRistinSiirto(99);
        assertEquals(0, tulos.siirronArvo);
    }

    @Test
    public void aloittaaTyhjallaLaudallaKeskelta() {
        Pelimerkki[][] pmArr = {
            {t, t, t},
            {t, t, t},
            {t, t, t}};
        Ristinolla rn = luoRistinolla(pmArr, 3);
        abKarsinta = new AlphaBetaKarsinta(rn, new MunEvaluoija());
        Hakutulos tulos = abKarsinta.etsiRistinSiirto(99);
        assertEquals(1, tulos.parasSiirto.x);
        assertEquals(1, tulos.parasSiirto.y);
    }

    @Test
    public void karsiiHakupuuta() {
        Pelimerkki[][] pmArr = {
            {t, t, t},
            {t, t, t},
            {t, t, t}};
        Ristinolla rn = luoRistinolla(pmArr, 3);
        abKarsinta = new AlphaBetaKarsinta(rn, new MunEvaluoija());
        Hakutulos tulos = abKarsinta.etsiRistinSiirto(99);
        assertTrue(tulos.evaluoitujaTilanteita < 10000);
    }
    
    @Test
    public void ratkaiseeTicTacToenAlleSekunnissa() {
        Pelimerkki[][] pmArr = {
            {t, t, t},
            {t, t, t},
            {t, t, t}};
        Ristinolla rn = luoRistinolla(pmArr, 3);
        abKarsinta = new AlphaBetaKarsinta(rn, new MunEvaluoija());
        Hakutulos tulos = abKarsinta.etsiRistinSiirto(99);
        // koko hakupuun nodet: 9! = 362880
        assertTrue(tulos.hakuaika < 1000);
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
