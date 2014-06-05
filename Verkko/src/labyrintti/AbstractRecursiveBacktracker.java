/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package labyrintti;

import java.util.Arrays;
import util.Taulukko;
import verkko.Solmu;

public abstract class AbstractRecursiveBacktracker extends Labyrintitin2D {

    /**
     * Kutsuu Labyrintitinin konstruktorin. Initialisoi käyty taulukon oikean
     * kokoiseksi ja falseksi. Luo myös randomin.
     *
     * @param solmut
     */
    public AbstractRecursiveBacktracker(Solmu[][] solmut) {
        super(solmut);
    }

    /**
     * Tämä kutsutaan kun halutaan suorittaa se mitä tämä luokka tekee. Eli
     * labyrintittää labyrintin recursive backtrackingilla.
     *
     * @return
     */
    @Override
    public void labyrintita() {
        recur(0, 0);
    }
    
    abstract boolean onkoKayty(Solmu s);
    
    abstract void kayty(Solmu s);

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
        final Solmu nyt = solmut[x][y];
        kayty(nyt);
        Solmu[] naapurit = naapurit(nyt);
        if (naapurit.length > 0) {
            //sekoitetaan niin valitaan randomisti jokin naapuri.
            Taulukko.sekoita(naapurit);
            for (Solmu naapuri : naapurit) {
                if (!onkoKayty(naapuri)) {
                    int naapuriX = naapuri.kokonaislukuKoordinaatti(0);
                    int naapuriY = naapuri.kokonaislukuKoordinaatti(1);
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
     *
     * @param s
     * @return
     */
    abstract Solmu[] naapurit(Solmu s);
    
}
