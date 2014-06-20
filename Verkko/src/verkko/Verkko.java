/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package verkko;

import cli.CLI;

/**
 * Main luokka
 *
 * @author Arvoitusmies
 */
public class Verkko {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Labyrintti2D l = new Labyrintti2D(KORKEUS, LEVEYS);
//        l.setLabyrintitin(new Prim(l.getSolmut()));
//        l.labyrintitaLabyrintti();
//        System.out.println(l);
//        Solmu alku = l.getSolmut()[0][0];
//        Solmu maali = l.getSolmut()[KORKEUS - 1][LEVEYS - 1];
//        Astar astar = new Astar(alku, maali, new TaksimiehenEtaisyys());
//        astar.suorita();
//        System.out.println(l.printtaaReittiLabyrintissa(astar.getReitti(), maali));

        CLI cli = new CLI();
        cli.juokse();

    }

}
