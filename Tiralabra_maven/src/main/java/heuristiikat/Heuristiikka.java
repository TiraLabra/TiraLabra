package heuristiikat;

import tietorakenteet.Node;
import tietorakenteet.Node;

/**
 * Heuristiikan interface.
 * Voidaan antaa hakualgoritmille parametreina tämän toteuttavia varsinaisia
 * heuristiikkoja.
 * 
 */
public interface Heuristiikka {
    public int laskeArvio(Node alku, Node loppu);
}
