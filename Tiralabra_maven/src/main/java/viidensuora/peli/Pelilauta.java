package viidensuora.peli;

/**
 * Pelilautaa kuvaava luokka, johon pelaajat asettavat pelimerkkinsä.
 *
 * @author juha
 */
public class Pelilauta {

    /**
     * Pelilaudan leveys.
     */
    private final int leveys;

    /**
     * Pelilaudan korkeus.
     */
    private final int korkeus;

    /**
     * Kuinka monta pelimerkkiä mahtuu yhteensä laudalle.
     */
    private final int maxMerkkeja;

    /**
     * 2D-taulukko Pelimerkeille.
     */
    private final Pelimerkki[][] pelilauta;

    /**
     * Montako merkkiä on laudalla tällä hetkellä.
     */
    private int merkkeja;

    public Pelilauta(int korkeus, int leveys) {
        if (leveys < 1 || korkeus < 1) {
            throw new IllegalArgumentException("Pelilaudan koko pienempi kuin 1");
        }
        this.korkeus = korkeus;
        this.leveys = leveys;
        this.maxMerkkeja = leveys * korkeus;
        this.pelilauta = new Pelimerkki[korkeus][leveys];
        this.merkkeja = 0;
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    /**
     * Tarkistaa onko koordinaatissa Risti.
     *
     * @param i Rivi.
     * @param j Sarake.
     * @return TRUE jos on, FALSE jos ei.
     */
    public boolean onRisti(int i, int j) {
        return pelilauta[i][j] instanceof Risti;
    }

    /**
     * Tarkistaa onko koordinaatissa Nolla.
     *
     * @param i Rivi.
     * @param j Sarake.
     * @return TRUE jos on, FALSE jos ei.
     */
    public boolean onNolla(int i, int j) {
        return pelilauta[i][j] instanceof Nolla;
    }

    public Pelimerkki getPelimerkki(int i, int j) {
        if (!koordinaattiLaudalla(i, j)) {
            return null;
        }
        return pelilauta[i][j];
    }

    /**
     * Lisää uuden pelimerkin laudalle ja kasvattaa pelimerkkilaskuria.
     *
     * @param pm
     */
    public void lisaaMerkki(Pelimerkki pm) {
        pelilauta[pm.getI()][pm.getJ()] = pm;
        merkkeja++;
    }

    /**
     * Asettaa Ristin annettuun koordinaattiin mikäli se on vapaa.
     *
     * @param i Rivi.
     * @param j Sarake.
     */
    public void asetaRisti(int i, int j) {
        if (ruutuVapaa(i, j)) {
            pelilauta[i][j] = new Risti(i, j);
            merkkeja++;
        }
    }

    /**
     * Asettaa Nollan annettuun koordinaattiin mikäli se on vapaa.
     *
     * @param i Rivi.
     * @param j Sarake.
     */
    public void asetaNolla(int i, int j) {
        if (ruutuVapaa(i, j)) {
            pelilauta[i][j] = new Nolla(i, j);
            merkkeja++;
        }
    }

    /**
     * Poistaa merkin laudalta ja vähentää pelimerkkilaskuria.
     *
     * @param i
     * @param j
     */
    public void poistaMerkki(int i, int j) {
        if (!ruutuVapaa(i, j)) {
            pelilauta[i][j] = null;
            merkkeja--;
        }
    }

    /**
     * Tarkistaa onko koordinaatin ruutu vapaa.
     *
     * @param i Rivi.
     * @param j Sarake.
     * @return TRUE jos vapaa, FALSE jos varattu.
     */
    public boolean ruutuVapaa(int i, int j) {
        return pelilauta[i][j] == null;
    }

    /**
     * Montako tyhjää ruutua laudalla.
     *
     * @return Tyhjien ruutujen määrä.
     */
    public int vapaitaRuutuja() {
        return maxMerkkeja - merkkeja;
    }

    /**
     * Tarkistaa onko pelilauta täynnä.
     *
     * @return TRUE jos täynnä, FALSE jos ei.
     */
    public boolean taynna() {
        return merkkeja >= maxMerkkeja;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                if (pelilauta[i][j] != null) {
                    sb.append(pelilauta[i][j]);
                } else {
                    sb.append(".");
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Tarkistaa onko koordinaatti laillinen.
     *
     * @param i Rivi.
     * @param j Sarake.
     * @return TRUE jos ok, FALSE jos ei.
     */
    private boolean koordinaattiLaudalla(int i, int j) {
        return i >= 0 && i < korkeus && j >= 0 && j < leveys;
    }

}
