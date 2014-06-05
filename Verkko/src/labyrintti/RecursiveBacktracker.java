/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package labyrintti;

import java.util.Arrays;
import util.Taulukko;
import verkko.Solmu;

/**
 * Labyrintti2D labyrintitintoteutus palaavalla syvyyssuuntaisella haulla.
 *
 * @author Arvoitusmies
 */
public class RecursiveBacktracker extends Labyrintitin {

    /**
     * Tämä taulukko vastaa solmut taulukon kohtia ja merkkaa onko kyseinen
     * solmu jo käsitelty.
     */
    protected final Boolean[][] kayty;

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
        return solmut.clone();
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
    protected void recur(int x, int y) {
        kayty[x][y] = true;
        Solmu[] naapurit = naapurit(x, y);
        if (naapurit.length > 0) {
            //sekoitetaan niin valitaan randomisti jokin naapuri.
            for (Solmu naapuri : naapurit) {
                int naapuriX = naapuri.kokonaislukuKoordinaatti(0);
                int naapuriY = naapuri.kokonaislukuKoordinaatti(1);
                if (!kayty[naapuriX][naapuriY]) {
                    Solmu nyt = solmut[x][y];
                    luoNaapuruudet(nyt, naapuri);
                    recur(naapuriX, naapuriY);
                }
            }
        }
    }

    /**
     * Lisää toinen toisensa naapureiksi.
     *
     * @param nyt
     * @param naapuri
     */
    protected void luoNaapuruudet(Solmu nyt, Solmu naapuri) {
        nyt.lisaaNaapuri(naapuri, 1.0);
        naapuri.lisaaNaapuri(nyt, 1.0);
    }

    /**
     * Palauttaa naapurit, eli neljäsä pääilmansuunnassa olevat solmut.
     *
     * @param x
     * @param y
     * @return
     */
    protected Solmu[] naapurit(int x, int y) {
        Solmu[] naapurit = new Solmu[4];
        //"vasen"
        if (x > 0) {
            naapurit[0] = solmut[x - 1][y];
        }
        //"ylä"
        if (y > 0) {
            naapurit[1] = solmut[x][y - 1];
        }
        //"oikea"
        if (x < solmut.length - 1) {
            naapurit[2] = solmut[x + 1][y];
        }
        //"ala"
        if (y < solmut[0].length - 1) {
            naapurit[3] = solmut[x][y + 1];
        }
        final Object[] nullitPoistettu = Taulukko.poistaNullit(naapurit);
        Solmu[] paluu = Arrays.copyOf(nullitPoistettu, nullitPoistettu.length, Solmu[].class);
        Taulukko.sekoita(paluu);
        return paluu;
    }

}
