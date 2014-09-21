
package com.mycompany.tiralabra_maven.tietorakenteet;

import java.util.TreeMap;

/**
 *  javan Treemappia käyttävä luokka muiden luokkien toiminnan testaamiseen
 * @author Markus
 */
public class Testipuu extends TreeMap<Integer, Integer> implements Hakupuu{

    
    public void lisaa(int arvo) {
        super.put(arvo, arvo);
    }

    public boolean hae(int arvo) {
        return super.containsKey(arvo);
    }

    public void poista(int arvo) {
        super.remove(arvo);
    }

    public void lisaaKaikki(int[] arvot) {
        for (int i : arvot) {
            super.put(i, i);
        }
    }    
}
