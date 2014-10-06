package viidensuora.ai;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import viidensuora.logiikka.Koordinaatti;
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
    public void voitto() {        
        Pelimerkki[][] pmArr = {
            {x, o, o},
            {t, x, x},
            {o, t, t}};
        Ristinolla rn = luoRistinolla(pmArr, 3);
        abKarsinta = new AlphaBetaKarsinta(rn, new MunEvaluoija());
        //assertEquals(Integer.MAX_VALUE, ai.alphaBeta(9, Integer.MIN_VALUE, Integer.MAX_VALUE, true, null));
        //assertTrue(ai.alphaBeta(9, Integer.MIN_VALUE, Integer.MAX_VALUE, true, null) > 0);
        Koordinaatti k = abKarsinta.etsiRistinSiirto(99);
        System.out.println(k.x + ", " + k.y);
        assertTrue((k.x == 0 && k.y == 1) || (k.x == 2 && k.y == 2));
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
