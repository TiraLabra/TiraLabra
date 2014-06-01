/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package verkko;

import labyrintti.Labyrintti2D;
import labyrintti.RecursiveBacktracker;
import polunetsinta.Astar;
import polunetsinta.TaksimiehenEtaisyys;

public class Verkko {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Labyrintti2D l = new Labyrintti2D(KORKEUS, LEVEYS);
        l.setLabyrintitin(new RecursiveBacktracker(l.getSolmut()));
        l.labyrintitaLabyrintti();
        System.out.println(l.toString());
        Solmu alku = l.getSolmut()[0][0];
        Solmu maali = l.getSolmut()[KORKEUS - 1][LEVEYS - 1];
        Astar astar = new Astar(alku, maali, new TaksimiehenEtaisyys());
        astar.printtaaReittiSolmutVaarinpain(maali);
        System.out.println(l.printtaaReittiLabyrintissa(astar.getReitti()));
    }
    private static final int LEVEYS = 30;
    private static final int KORKEUS = 12;

}
