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
public class RecursiveBacktracker2D extends AbstractRecursiveBacktracker {

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
    public RecursiveBacktracker2D(Solmu[][] solmut) {
        super(solmut);
        kayty = new Boolean[solmut.length][solmut[0].length];
        for (int i = 0; i < solmut.length; i++) {
            for (int j = 0; j < solmut[0].length; j++) {
                kayty[i][j] = false;
            }
        }
    }

    @Override
    boolean onkoKayty(Solmu s) {
        int x = s.kokonaislukuKoordinaatti(0);
        int y = s.kokonaislukuKoordinaatti(1);
        return kayty[x][y];
    }

    @Override
    void kayty(Solmu s) {
        int x = s.kokonaislukuKoordinaatti(0);
        int y = s.kokonaislukuKoordinaatti(1);
        kayty[x][y] = true;
    }

    @Override
    Solmu[] naapurit(Solmu s) {

        int x = s.kokonaislukuKoordinaatti(0);
        int y = s.kokonaislukuKoordinaatti(1);
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
        return paluu;

    }
}
