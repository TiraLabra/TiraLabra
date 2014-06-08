
package heuristiikat;

import heuristiikat.Heuristiikka;
import tietorakenteet.Node;
import tietorakenteet.Node;

/**
 *
 */
public class Manhattan implements Heuristiikka {

    public int laskeArvio(Node alku, Node loppu) {
        int tulos = 0;
        tulos = Math.abs(alku.getRivi()-loppu.getRivi()) + Math.abs(alku.getSarake()-loppu.getSarake());
        
        return tulos;
    }
    
    

}
