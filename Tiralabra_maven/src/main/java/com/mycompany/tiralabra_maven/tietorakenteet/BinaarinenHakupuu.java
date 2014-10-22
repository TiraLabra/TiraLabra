package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Binäärisen hakupuun implementaatio. Perii Ylihakupuun. Käyttää solmuinaan luokan BinaarinenPuusolmu olioita. 
 *
 * @see BinaarinenPuusolmu
 * @see YliHakupuu
 * @see http://en.wikipedia.org/wiki/Binary_search_tree
 * @author Markus
 */
public class BinaarinenHakupuu extends YliHakupuu {

    public void lisaa(int avain) {
        Puusolmu uusi = new BinaarinenPuusolmu(avain);
        lisaa(uusi);
    }
    
    public String getNimi() {
        return "Binäärinen hakupuu";
    }
}