
package heuristiikat;

import tietorakenteet.Node;

/**
 *
 */
public class Euklidinen implements Heuristiikka {

    private static final int askel = 10;

    public int laskeArvio(Node alku, Node loppu) {
        int n = (int)Math.sqrt(
                    Math.pow( ( (alku.getRivi()-loppu.getRivi() )*askel), 2) +
                    Math.pow( ( (alku.getSarake()-loppu.getSarake() )*askel), 2));
                
        return n;
    }
    
    
    
}
