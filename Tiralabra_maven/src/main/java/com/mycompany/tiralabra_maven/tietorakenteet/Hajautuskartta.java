
package com.mycompany.tiralabra_maven.tietorakenteet;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Ylivuotolistoja sekä jakojäännösmenetelmään perustuvaa hajautusfunktiota
 * hyödyntävä hajautuskartta. Hajautusfunktion yksinkertaistamiseksi ja tämän
 * projektin tarpeita ajatellen hajautuskartan avaimet voivat olla vain tyyppiä
 * <b>char</b>.
 * 
 * @author John Lång
 * @param <Character>
 * @param <V> 
 */
public final class Hajautuskartta<V> {
    
    private final AvainArvoJono<Character, V>[] YLIVUOTOLISTAT;

    public Hajautuskartta(final int TAULUN_PITUS) {
        this.YLIVUOTOLISTAT = new AvainArvoJono[TAULUN_PITUS];
        for (int i = 0; i < TAULUN_PITUS; i++) {
            YLIVUOTOLISTAT[i] = new AvainArvoJono<Character, V>();
        }
    }

    public void lisaa(char avain, V arvo) {
        YLIVUOTOLISTAT[hajauta(avain)].lisaa(avain, arvo);
    }
    
    private int hajauta(char avain) {
        return (int) avain % YLIVUOTOLISTAT.length;
    }
    
    public V hae(char avain) {
        return YLIVUOTOLISTAT[hajauta(avain)].hae(avain);
    }
    
}
