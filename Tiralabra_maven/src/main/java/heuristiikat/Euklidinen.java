package heuristiikat;

import tietorakenteet.Node;

/**
 * Euklidinen heuristiikka.
 * Pythagoran lausetta vastaava laskentatapa.
 */
public class Euklidinen implements Heuristiikka {

    /**
     * Muuttuja joka kertoo yhden askeleen liikkumakustannuksen.
     */
    private static final int askel = 10;

    public int laskeArvio(Node alku, Node loppu) {
        int n = (int)Math.sqrt(
                    Math.pow( ( (alku.getRivi()-loppu.getRivi() )*askel), 2) +
                    Math.pow( ( (alku.getSarake()-loppu.getSarake() )*askel), 2));
                
        return n;
    }
    
    
    
}
