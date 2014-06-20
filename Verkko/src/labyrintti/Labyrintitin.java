/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package labyrintti;

import java.util.Arrays;
import util.Lista;
import util.Taulukko;
import verkko.Solmu;

/**
 * Abstrakti luokka Labyrintti2D labyrintittäville algoritmeille
 *
 * @author Arvoitusmies
 */
public abstract class Labyrintitin {

    /**
     * Tähän talletetaan solmut.
     */
    final Solmu[][] solmut;
    /**
     * Tämä taulukko vastaa solmut taulukon kohtia ja merkkaa onko kyseinen
     * solmu jo käsitelty.
     */
    protected final Boolean[][] kayty;

    /**
     * Asettaa solmut kenttään, initialisoi käyty taulukon oikean kokoiseksi ja
     * falseksi.
     *
     * @param solmut
     */
    public Labyrintitin(Solmu[][] solmut) {
        this.solmut = solmut;
        kayty = new Boolean[solmut.length][solmut[0].length];
        for (int i = 0; i < solmut.length; i++) {
            for (int j = 0; j < solmut[0].length; j++) {
                kayty[i][j] = false;
            }
        }
    }

    /**
     * "Labyrintittää"
     *
     */
    public abstract void labyrintita();

    /**
     * Tämän labyrintinkohdan mahdolliset naapurit.
     *
     * @param s
     * @return
     */
    Lista<Solmu> naapurit(Solmu s) {
        int x = s.kokonaislukuKoordinaatti(0);
        int y = s.kokonaislukuKoordinaatti(1);
//        Solmu[] naapurit = new Solmu[4];
        Lista<Solmu> naapurit = new Lista<>(4);
        if (x > 0) {
            naapurit.lisaa(solmut[x - 1][y]);
        }
        if (y > 0) {
            naapurit.lisaa(solmut[x][y - 1]);
        }
        if (x < solmut.length - 1) {
            naapurit.lisaa(solmut[x + 1][y]);
        }
        if (y < solmut[0].length - 1) {
            naapurit.lisaa(solmut[x][y + 1]);
        }
//        final Object[] nullitPoistettu = Taulukko.poistaNullit(naapurit);
//        Solmu[] paluu = Arrays.copyOf(nullitPoistettu, nullitPoistettu.length, Solmu[].class);
        return naapurit;
    }

    /**
     * Asettaa solmun s käydyksi
     *
     * @param s
     */
    void kayty(Solmu s) {
        int x = s.kokonaislukuKoordinaatti(0);
        int y = s.kokonaislukuKoordinaatti(1);
        kayty[x][y] = true;
    }

    /**
     * Lisää toinen toisensa naapureiksi.
     *
     * @param nyt
     * @param naapuri
     */
    static protected void luoNaapuruudet(Solmu nyt, Solmu naapuri) {
        nyt.lisaaNaapuri(naapuri, 1.0);
        naapuri.lisaaNaapuri(nyt, 1.0);
    }

    /**
     * True jos käyty
     *
     * @param s
     * @return
     */
    boolean onkoKayty(Solmu s) {
        int x = s.kokonaislukuKoordinaatti(0);
        int y = s.kokonaislukuKoordinaatti(1);
        return kayty[x][y];
    }

}
