package viidensuora.peli;

/**
 * Pelilogiikka, joka pyörittää peliä.
 *
 * @author juha
 */
public class Peli {

    /**
     * Pelilauta, jolla pelimerkit sijaitsevat.
     */
    private final Pelilauta pelilauta;

    /**
     * Peräkkäisten pelimerkkien pituus, joka voitolta vaaditaan. (Viisi..?)
     */
    private final int voittavaPituus;

    /**
     * Seuraavan pelaajan vuoro.
     */
    private boolean ristinVuoro;

    public Peli(int korkeus, int leveys, int voittavaPituus) {
        this.pelilauta = new Pelilauta(korkeus, leveys);
        this.voittavaPituus = voittavaPituus;
        this.ristinVuoro = true;
    }

    public Peli(Pelilauta pelilauta, int voittavaPituus) {
        this.pelilauta = pelilauta;
        this.voittavaPituus = voittavaPituus;
        this.ristinVuoro = true;
    }

    public Pelilauta getPelilauta() {
        return pelilauta;
    }

    public int getVoittavaPituus() {
        return voittavaPituus;
    }

    /**
     * Lisää uuden pelimerkin pelilaudalle ja vaihtaa pelivuoroa. Asetettava
     * Pelimerkki valitaan pelivuoron perusteella.
     *
     * @param i Rivi, jolle uusi pelimerkki asetetaan.
     * @param j Sarake, jolle uusi pelimerkki asetetaan.
     */
    public void lisaaMerkki(int i, int j) {
        if (pelilauta.ruutuVapaa(i, j)) {
            if (ristinVuoro) {
                pelilauta.asetaRisti(i, j);
            } else {
                pelilauta.asetaNolla(i, j);
            }
            ristinVuoro = !ristinVuoro;
        }
    }

    /**
     * Tarkistaa muodostuuko voittavan pituinen suora kun käytetään parametrina
     * saatua Pelimerkkiä.
     *
     * @param pm Pelimerkki jota tarkastellaan.
     * @return TRUE jos suora on tarpeeksi pitkä, FALSE jos ei.
     */
    public boolean siirtoVoitti(Pelimerkki pm) {
        return pisinSuora(pm) >= voittavaPituus;
    }

    /**
     * Laskee pisimmän suoran joka muodostuu, kun käytetään parametrina saatua
     * Pelimerkkiä. Suora voi olla suunnaltaan horisontaalinen, vertikaalinen
     * tai diagonaalinen.
     *
     * @param pm Pelimerkki, jota tarkastellaan.
     * @return Pisin suora. 0
     */
    public int pisinSuora(Pelimerkki pm) {
        if (pm == null) {
            return 0;
        }
        int hor = (laske(pm, 0, -1) + laske(pm, 0, 1)) - 1;
        int ver = (laske(pm, -1, 0) + laske(pm, 1, 0)) - 1;
        int diag1 = (laske(pm, -1, -1) + laske(pm, 1, 1)) - 1;
        int diag2 = (laske(pm, 1, -1) + laske(pm, -1, 1)) - 1;
        return Math.max(hor, Math.max(ver, Math.max(diag1, diag2)));
    }

    /**
     * Laskee suoran pituuden tietystä suunnasta.
     *
     * @param pm Pelimerkki, josta lasku lähtee liikkeelle.
     * @param di Delta i, eli missä suunnassa liikutaan vertikaalisesti.
     * @param dj Delta j, eli missä suunnassa liikutaan horisontaalisesti.
     * @return Suoran pituus.
     */
    private int laske(Pelimerkki pm, int di, int dj) {
        if (pm == null) {
            return 0;
        }
        int n = 1, i = pm.getI(), j = pm.getJ();
        while (pm.equals(pelilauta.getPelimerkki(i += di, j += dj))) {
            n++;
        }
        return n;
    }

    /*
     public int pisinSuora2(Pelimerkki alku) {
     int v = 0, o = 0, y = 0, a = 0, vy = 0, oy = 0, oa = 0, va = 0;
     for (int delta = 1; delta <= voittavaPituus; delta++) {
     v += pelilauta.getPelimerkki(i, j - delta) == pm ? 1 : 0;
     o += pelilauta.getPelimerkki(i, j + delta) == pm ? 1 : 0;

     y += pelilauta.getPelimerkki(i - delta, j) == pm ? 1 : 0;
     a += pelilauta.getPelimerkki(i + delta, j) == pm ? 1 : 0;

     vy += pelilauta.getPelimerkki(i - delta, j - delta) == pm ? 1 : 0;
     oa += pelilauta.getPelimerkki(i + delta, j + delta) == pm ? 1 : 0;

     va += pelilauta.getPelimerkki(i + delta, j - delta) == pm ? 1 : 0;
     oy += pelilauta.getPelimerkki(i - delta, j + delta) == pm ? 1 : 0;
     }
     return 0;
     }*/
}
