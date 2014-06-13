/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package verkko;

import labyrintti.Labyrintti2D;
import labyrintti.Prim;
import polunetsinta.Astar;
import polunetsinta.TaksimiehenEtaisyys;

/**
 * Main luokka
 *
 * @author Arvoitusmies
 */
public class Verkko {

    /**
     *
     */
    private static final int LEVEYS = 50;

    /**
     *
     */
    private static final int KORKEUS = 20;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Labyrintti2D l = new Labyrintti2D(KORKEUS, LEVEYS);
        l.setLabyrintitin(new Prim(l.getSolmut()));
        l.labyrintitaLabyrintti();
        System.out.println(l);
        Solmu alku = l.getSolmut()[0][0];
        Solmu maali = l.getSolmut()[KORKEUS - 1][LEVEYS - 1];
        Astar astar = new Astar(alku, maali, new TaksimiehenEtaisyys());
        astar.suorita();
        System.out.println(l.printtaaReittiLabyrintissa(astar.getReitti(), maali));

    }

}
