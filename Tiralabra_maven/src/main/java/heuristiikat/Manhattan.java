package heuristiikat;

import tietorakenteet.Node;

/**
 * Manhattan-heuristiikka.
 * Laskee etäisyysarvion laskemalla yhteen sekä x- että y-koordinaattien erotuksen,
 * eli kuten jättäisi diagonaalisen liikkumisen huomioimatta.
 */
public class Manhattan implements Heuristiikka {
    
    /**
     * Muuttuja joka kertoo yhden askeleen liikkumakustannuksen.
     */
    private int askel = 10;
    
    public int laskeArvio(Node alku, Node loppu) {
        int tulos = 0;
        tulos = Math.abs(alku.getRivi()-loppu.getRivi())*askel + Math.abs(alku.getSarake()-loppu.getSarake())*askel;
        
        return tulos;
    }
    
    

}
