
package heuristiikat;

import heuristiikat.Heuristiikka;
import tietorakenteet.Node;
import tietorakenteet.Node;

/**
 *
 */
public class Manhattan implements Heuristiikka {

    private int askel = 10;
    
    public int laskeArvio(Node alku, Node loppu) {
        int tulos = 0;
        tulos = Math.abs(alku.getRivi()-loppu.getRivi())*askel + Math.abs(alku.getSarake()-loppu.getSarake())*askel;
        
        return tulos;
    }
    
    

}
