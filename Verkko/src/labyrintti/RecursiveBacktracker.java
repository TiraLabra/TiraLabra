/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package labyrintti;

import java.util.Arrays;
import java.util.Random;
import util.Taulukko;
import verkko.Solmu;

/**
 *
 * @author Arvoitusmies
 */
public class RecursiveBacktracker extends Labyrintitin {

    /**
     * Tämä taulukko vastaa solmut taulukon kohtia ja merkkaa onko kyseinen
     * solmu jo käsitelty.
     */
    private Boolean[][] kayty;

    /**
     * RNG eli Random Number God.
     */
    private Random r;

    /**
     * Kutsuu Labyrintitinin konstruktorin. Initialisoi käyty taulukon oikean
     * kokoiseksi ja falseksi. Luo myös randomin.
     *
     * @param solmut
     */
    public RecursiveBacktracker(Solmu[][] solmut) {
        super(solmut);
        kayty = new Boolean[solmut.length][solmut[0].length];
        for (int i = 0; i < solmut.length; i++) {
            for (int j = 0; j < solmut[0].length; j++) {
                kayty[i][j] = false;
            }
        }
        r = new Random();
    }

    /**
     * Tämä kutsutaan kun halutaan suorittaa se mitä tämä luokka tekee. Eli
     * labyrintittää labyrintin recursive backtrackingilla.
     *
     * @return
     */
    @Override
    public Solmu[][] labyrintitaLabyrintti() {
        recur(0, 0);
        return solmut;
    }

    /**
     * Tätä kutsitaan rekursiivisesti. Toiminta:
     *
     * 1. Merkkaa käydyksi
     *
     * 2. ottaa naapurit ja sekoittaa ne
     *
     * 3. jokainen naapuri vuorollaan, jollei ole käyty,
     *
     * a) luodaan x,y solmun ja naapurin väliin sivu (labyrintin käytävä)
     *
     * b) kutsutaan naapurille recur.
     *
     * @param x x-koordinaatti (solmut[x][y])
     * @param y y-koordinaatti
     */
    private void recur(int x, int y) {
        kayty[x][y] = true;
        Solmu[] naapurit = naapurit(x, y);
        if (naapurit.length > 0) {
            //sekoitetaan niin valitaan randomisti jokin naapuri.
            Taulukko.sekoita(naapurit);
            for (int i = 0; i < naapurit.length; i++) {
                Solmu naapuri = naapurit[i];
                int ksx = (int) Math.round(naapuri.koordinaatti(0));
                int ksy = (int) Math.round(naapuri.koordinaatti(1));
                if (!kayty[ksx][ksy]) {
                    solmut[x][y].lisaaNaapuri(naapuri, 1.0);
                    naapuri.lisaaNaapuri(solmut[x][y], 1.0);
                    recur(ksx, ksy);
                }
            }
        }
    }

    /**
     * Palauttaa naapurit, eli neljäsä pääilmansuunnassa olevat solmut.
     *
     * @param x
     * @param y
     * @return
     */
    private Solmu[] naapurit(int x, int y) {
        Solmu[] naapurit = new Solmu[4];
        if (x > 0) {
            naapurit[0] = solmut[x - 1][y];
        }
        if (y > 0) {
            naapurit[1] = solmut[x][y - 1];
        }
        if (x < solmut.length - 1) {
            naapurit[2] = solmut[x + 1][y];
        }
        if (y < solmut[0].length - 1) {
            naapurit[3] = solmut[x][y + 1];
        }
        final Object[] nullitPoistettu = Taulukko.poistaNullit(naapurit);
        Solmu[] paluu = Arrays.copyOf(nullitPoistettu, nullitPoistettu.length, Solmu[].class);
        return paluu;
    }

}
