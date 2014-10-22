package viidensuora.ai;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import viidensuora.logiikka.Pelimerkki;
import viidensuora.logiikka.Ristinolla;

public class MunEvaluoijaTest {

    private final Pelimerkki t = null;
    private final Pelimerkki x = Pelimerkki.RISTI;
    private final Pelimerkki o = Pelimerkki.NOLLA;

    private MunEvaluoija e;
    private Ristinolla rn;

    @Before
    public void setUp() {
        Pelimerkki[][] pmArr = {
            {x, x, x, t, x},
            {t, x, t, t, t},
            {t, o, x, o, x},
            {t, t, o, o, x},
            {t, x, t, t, t}};
        rn = luoRistinolla(pmArr, 4);
        e = new MunEvaluoija();
        e.setRistinolla(rn);
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
    public void tunnistaaEstetyn() {
        assertTrue(e.estetty(0, 0, Suunta.DIAG1));
        assertTrue(e.estetty(1, 1, Suunta.DIAG1));
        assertTrue(e.estetty(2, 2, Suunta.DIAG1));

        assertTrue(e.estetty(2, 3, Suunta.DIAG2));
        assertTrue(e.estetty(3, 2, Suunta.DIAG2));
    }

    @Test
    public void tunnistaaEiEstetyn() {
        assertTrue(!e.estetty(0, 0, Suunta.VAAKA));
        assertTrue(!e.estetty(1, 0, Suunta.VAAKA));
        assertTrue(!e.estetty(2, 0, Suunta.VAAKA));
        assertTrue(!e.estetty(3, 0, Suunta.VAAKA));
        assertTrue(!e.estetty(4, 0, Suunta.VAAKA));

        assertTrue(!e.estetty(2, 3, Suunta.VAAKA));
        assertTrue(!e.estetty(3, 3, Suunta.VAAKA));
    }

    @Test
    public void evaluoiVaakalinjatOikein() {
        assertEquals(68, e.evaluoiLinja(0, 0, Suunta.OIKEA), 0.01);
        assertEquals(4, e.evaluoiLinja(0, 1, Suunta.OIKEA), 0.01);
        assertEquals(0, e.evaluoiLinja(0, 2, Suunta.OIKEA), 0.01);
        assertEquals(-16, e.evaluoiLinja(0, 3, Suunta.OIKEA), 0.01);
        assertEquals(4, e.evaluoiLinja(0, 4, Suunta.OIKEA), 0.01);
    }

    @Test
    public void evaluoiPystylinjatOikein() {
        assertEquals(4, e.evaluoiLinja(0, 0, Suunta.ALAS), 0.01);
        assertEquals(0, e.evaluoiLinja(1, 0, Suunta.ALAS), 0.01);
        assertEquals(0, e.evaluoiLinja(2, 0, Suunta.ALAS), 0.01);
        assertEquals(-16, e.evaluoiLinja(3, 0, Suunta.ALAS), 0.01);
        assertEquals(20, e.evaluoiLinja(4, 0, Suunta.ALAS), 0.01);
    }

    @Test
    public void evaluoiDiagonaali1LinjatOikein() {
        assertEquals(0, e.evaluoiLinja(0, 0, Suunta.ALAOIKEA), 0.01);
        assertEquals(0, e.evaluoiLinja(1, 0, Suunta.ALAOIKEA), 0.01);
        assertEquals(0, e.evaluoiLinja(2, 0, Suunta.ALAOIKEA), 0.01);
        assertEquals(0, e.evaluoiLinja(3, 0, Suunta.ALAOIKEA), 0.01);
        assertEquals(0, e.evaluoiLinja(4, 0, Suunta.ALAOIKEA), 0.01);

        assertEquals(-16, e.evaluoiLinja(0, 1, Suunta.ALAOIKEA), 0.01);
        assertEquals(0, e.evaluoiLinja(0, 2, Suunta.ALAOIKEA), 0.01);
        assertEquals(0, e.evaluoiLinja(0, 3, Suunta.ALAOIKEA), 0.01);
        assertEquals(0, e.evaluoiLinja(0, 4, Suunta.ALAOIKEA), 0.01);
    }

    @Test
    public void evaluoiDiagonaali2LinjatOikein() {
        assertEquals(0, e.evaluoiLinja(0, 0, Suunta.ALAVASEN), 0.01);
        assertEquals(0, e.evaluoiLinja(1, 0, Suunta.ALAVASEN), 0.01);
        assertEquals(0, e.evaluoiLinja(2, 0, Suunta.ALAVASEN), 0.01);
        assertEquals(-4, e.evaluoiLinja(3, 0, Suunta.ALAVASEN), 0.01);
        assertEquals(8, e.evaluoiLinja(4, 0, Suunta.ALAVASEN), 0.01);

        assertEquals(0, e.evaluoiLinja(4, 1, Suunta.ALAVASEN), 0.01);
        assertEquals(0, e.evaluoiLinja(4, 2, Suunta.ALAVASEN), 0.01);
        assertEquals(0, e.evaluoiLinja(4, 3, Suunta.ALAVASEN), 0.01);
        assertEquals(0, e.evaluoiLinja(4, 4, Suunta.ALAVASEN), 0.01);
    }

    @Test
    public void evaluoiRivitOikein() {
        assertEquals(60, e.evaluoiRivit(), 0.01);
    }

    @Test
    public void evaluoiSarakkeetOikein() {
        assertEquals(8, e.evaluoiSarakkeet(), 0.01);
    }

    @Test
    public void evaluoiDiagonaalit1Oikein() {
        assertEquals(-16, e.evaluoiDiagonaalit1(), 0.01);
    }

    @Test
    public void evaluoiDiagonaalit2Oikein() {
        assertEquals(4, e.evaluoiDiagonaalit2(), 0.01);
    }

    @Test
    public void evaluoiPelitilanteenOikein() {
        assertEquals(56, e.evaluoiPelitilanne(rn), 0.01);
    }


}
