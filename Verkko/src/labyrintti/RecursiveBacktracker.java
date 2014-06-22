/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package labyrintti;

import util.Lista;
import verkko.Solmu;

/**
 * Palaava syvyyssuuntainen haku Labyrintti2D labyrintittämiseen.
 *
 * @author Arvoitusmies
 */
public class RecursiveBacktracker extends Labyrintitin {

    /**
     * Kutsuu Labyrintitinin konstruktorin. Initialisoi käyty taulukon oikean
     * kokoiseksi ja falseksi.
     *
     * @param solmut
     */
    public RecursiveBacktracker(Solmu[][] solmut) {
        super(solmut);

    }

    /**
     * Tämä kutsutaan kun halutaan suorittaa se mitä tämä luokka tekee. Eli
     * labyrintittää labyrintin recursive backtrackingilla.
     *
     */
    @Override
    public void labyrintita() {
        recur(0, 0);
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
        final Solmu nyt = solmut[x][y];
        kayty(nyt);
        Lista<Solmu> naapurit = naapurit(nyt);
        if (naapurit.getKoko() > 0) {
            //sekoitetaan niin valitaan randomisti jokin naapuri.
            naapurit.sekoita();
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

}
