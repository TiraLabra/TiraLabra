package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.AI.Solmu;
import java.util.Comparator;

/**
 * Luokka, jota käytetään solmuolioiden vertailuun
 * @author noora
 */
public class SolmujenVertailija implements Comparator<Solmu> {
    
    public SolmujenVertailija(){
        
    }

    /**
     * Metodin avulla voidaan vertailla kahden solmun suuruutta.
     * Vertailu perustuu solmujen arvoihin
     * @param s1 Ensimmäinen solmu
     * @param s2 Toinen solmu
     * @return 1 jos ensimmäinen oli suurempi ja -1 jos toinen oli suurempi
     */
    @Override
    public int compare(Solmu s1, Solmu s2) {
        if (s1.getArvo() < s2.getArvo()){
            return -1;
        } else if (s1.getArvo() == s2.getArvo()) {
            return 0;
        }
        return 1;
    }
    
}
