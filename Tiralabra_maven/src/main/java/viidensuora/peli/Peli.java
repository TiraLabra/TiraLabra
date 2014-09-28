package viidensuora.peli;

/**
 * Pelilogiikka, joka pyörittää peliä.
 *
 * @author juha
 */
public class Peli {

    public static final int TYHJA = 0;
    public static final int RISTI = 1;
    public static final int NOLLA = 2;
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

    private int voittaja;

    public Peli(int korkeus, int leveys, int voittavaPituus) {
        this.pelilauta = new Pelilauta(korkeus, leveys);
        this.voittavaPituus = voittavaPituus;
        this.ristinVuoro = true;
        this.voittaja = 0;
    }

    public Peli(Pelilauta pelilauta, int voittavaPituus) {
        this.pelilauta = pelilauta;
        this.voittavaPituus = voittavaPituus;
        this.ristinVuoro = true;
    }

    public Pelilauta getPelilauta() {
        return pelilauta;
    }

    public int getVoittaja() {
        return voittaja;
    }

    public int getVoittavaPituus() {
        return voittavaPituus;
    }

    public boolean onRistinVuoro() {
        return ristinVuoro;
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
            Pelimerkki pm = ristinVuoro ? new Risti(i, j) : new Nolla(i, j);
            pelilauta.lisaaMerkki(pm);
            if (siirtoVoitti(pm)) {
                this.voittaja = ristinVuoro ? RISTI : NOLLA;
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
        int hor = (laskePituus(pm, 0, -1) + laskePituus(pm, 0, 1)) - 1;
        int ver = (laskePituus(pm, -1, 0) + laskePituus(pm, 1, 0)) - 1;
        int diag1 = (laskePituus(pm, -1, -1) + laskePituus(pm, 1, 1)) - 1;
        int diag2 = (laskePituus(pm, 1, -1) + laskePituus(pm, -1, 1)) - 1;
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
    private int laskePituus(Pelimerkki pm, int di, int dj) {
        if (pm == null) {
            return 0;
        }
        int n = 1, i = pm.getI(), j = pm.getJ();
        while (pm.equals(pelilauta.getPelimerkki(i += di, j += dj))) {
            n++;
        }
        return n;
    }
}
