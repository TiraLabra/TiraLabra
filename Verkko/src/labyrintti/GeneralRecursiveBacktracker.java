/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package labyrintti;

import java.util.HashSet;
import verkko.Solmu;

public class GeneralRecursiveBacktracker extends AbstractRecursiveBacktracker {

    private HashSet<Solmu> kayty;

    public GeneralRecursiveBacktracker(Solmu[][] solmut) {
        super(solmut);
        kayty = new HashSet<>(solmut.length * solmut[0].length);
    }

    @Override
    boolean onkoKayty(Solmu s) {
        return kayty.contains(s);
    }

    @Override
    void kayty(Solmu s) {
        kayty.add(s);
    }

    @Override
    Solmu[] naapurit(Solmu s) {
        return s.getNaapuriSolmut().toArray();
    }

}
