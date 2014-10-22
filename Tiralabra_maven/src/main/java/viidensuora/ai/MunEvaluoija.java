package viidensuora.ai;

import viidensuora.logiikka.Pelimerkki;
import viidensuora.logiikka.Ristinolla;

/**
 * Etsintämetodeissa käytettävä Evaluointimetodi, joka arvioi pelitilanteen
 * heuristisen arvon. Käytännössä laskee voittoon kykenevien pelimerkkijonojen
 * pituuden. Mitä pitempiä ja mitä enemmän suoria on, sitä enemmän pelaajan arvo
 * kasvaa.
 *
 * @author juha
 */
public class MunEvaluoija implements Evaluointimetodi {

    /**
     * Pelitilanne
     */
    private Ristinolla rn;

    public void setRistinolla(Ristinolla rn) {
        this.rn = rn;
    }

    /**
     * Laskee koko pelitilanteen heuristisen arvon.
     *
     * @return Mitä suurempi luku, sitä todennäköisemmin Risti voittaa. Mitä
     * pienempi, Nolla voittaa. 0 tasan.
     */
    public int evaluoiPelitilanne(Ristinolla rn) {
        this.rn = rn;
        return (evaluoiRivit() + evaluoiSarakkeet()
                + evaluoiDiagonaalit1() + evaluoiDiagonaalit2());
    }

    /**
     * Laskee kaikkien rivieen arvot.
     *
     * @return Rivien yhteenlaskettu arvo.
     */
    public int evaluoiRivit() {
        int arvo = 0;
        for (int y = 0; y < rn.korkeus; y++) {
            arvo += evaluoiLinja(0, y, Suunta.OIKEA);
        }
        return arvo;
    }

    /**
     * Laskee kaikkien sarakkeiden arvot.
     *
     * @return Sarakkeiden yhteenlaskettu arvo.
     */
    public int evaluoiSarakkeet() {
        int arvo = 0;
        for (int x = 0; x < rn.leveys; x++) {
            arvo += evaluoiLinja(x, 0, Suunta.ALAS);
        }
        return arvo;
    }

    /**
     * Laskee kaikkien laskevien diagonaalien arvot. Ohittaa ne kulmat, joissa
     * ei voi muodostua voittavaa suoraa.
     *
     * @return Laskevien diagonaalien yhteenlaskettu arvo.
     */
    public int evaluoiDiagonaalit1() {
        int arvo = 0;
        for (int x = rn.leveys - rn.voittavaPituus; x >= 0; x--) {
            arvo += evaluoiLinja(x, 0, Suunta.ALAOIKEA);
        }
        for (int y = rn.korkeus - rn.voittavaPituus; y >= 1; y--) {
            arvo += evaluoiLinja(0, y, Suunta.ALAOIKEA);
        }
        return arvo;
    }

    /**
     * Laskee kaikkien nousevien diagonaalien arvot. Ohittaa ne kulmat, joissa
     * ei voi muodostua voittavaa suoraa.
     *
     * @return Nousevien diagonaalien yhteenlaskettu arvo.
     */
    public int evaluoiDiagonaalit2() {
        int arvo = 0;
        for (int x = rn.voittavaPituus - 1; x < rn.leveys; x++) {
            arvo += evaluoiLinja(x, 0, Suunta.ALAVASEN);
        }
        int x = rn.leveys - 1;
        for (int y = rn.korkeus - rn.voittavaPituus; y >= 1; y--) {
            arvo += evaluoiLinja(x, y, Suunta.ALAVASEN);
        }
        return arvo;
    }

    /**
     * Laskee yhden linjan arvon tietyssä suunnassa. Käytännössä rivi, sarake
     * tai diagonaali. Pelaaja kasvattaa arvoaan jokaisesta linjalla olevasta
     * n:n pituisesta suorasta 4^n pistettä. Siis mitä pitempi suora, sitä
     * enemmän pisteitä. Suoria, jotka eivät voi enää muodostaa voittavaa suoraa
     * ei huomioida lainkaan.
     *
     * @param x x-koordinaatti josta laskenta aloitetaan
     * @param y y-koordinaatti josta laskenta aloitetaan
     * @param suunta suunta johon päin laskenta etenee
     * @return Ristin pisteet miinus Nollan pisteet
     */
    public int evaluoiLinja(int x, int y, Suunta suunta) {
        int arvo = 0;
        while (rn.onLaudalla(x, y)) {
            Pelimerkki pm = rn.getMerkki(x, y);
            int pituus = rn.perakkaisiaMerkkeja(x, y, suunta);
            if (pm != null && !estetty(x, y, suunta)) {
                double a = Math.pow(4, pituus);
                arvo += (pm == Pelimerkki.RISTI) ? a : -a;
            }
            x += pituus * suunta.deltaX;
            y += pituus * suunta.deltaY;
        }
        return arvo;
    }

    /**
     * Tarkistaa voiko pelimerkki muodostaa voittavan suoran vai onko se estetty
     * vastustajan pelimerkeillä tai seinällä. Suora joka on estetty ei ole
     * minkään arvoinen.
     *
     * @param x merkin x-koordinaatti, josta tarkistus tapahtuu
     * @param y merkin y-koordinaatti, josta tarkistus tapahtuu
     * @param s Suunta josta tarkistetaan. VAAKA, PYSTY tai DIAGONAALIT
     * @return true jos merkki ei voi muodostaa voittavaa suoraa tietyssä
     * suunnassa, false jos voi
     */
    public boolean estetty(int x, int y, Suunta s) {
        Pelimerkki alku = rn.getMerkki(x, y);
        if (alku == null) {
            return false;
        }
        int n = 1;
        int x1 = x, y1 = y;
        while (rn.onLaudalla(x1 += s.deltaX, y1 += s.deltaY)
                && ((rn.getMerkki(x1, y1)) == alku || rn.ruutuOnTyhja(x1, y1))) {
            n++;
        } // vastakkainen suunta
        while (rn.onLaudalla(x -= s.deltaX, y -= s.deltaY)
                && ((rn.getMerkki(x, y)) == alku || rn.ruutuOnTyhja(x, y))) {
            n++;
        }
        return n < rn.voittavaPituus;
    }

}
