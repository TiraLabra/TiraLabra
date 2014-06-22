package heuristiikat;

import tietorakenteet.Node;

/**
 * Dijkstra-heuristiikka, triviaali Astarin erityistapaus.
 * Palauttaa aina nollan, eli ei arvioi maalimatkaa ollenkaan.
 */
public class Dijkstra implements Heuristiikka {

    public int laskeArvio(Node alku, Node loppu) {
        return 0;
    }

}
