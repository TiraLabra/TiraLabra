
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
        tulos = Math.abs(alku.getX()-loppu.getX()) + Math.abs(alku.getY()-loppu.getY());
        
        return tulos;
    }
    
    

}
