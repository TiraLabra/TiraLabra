
package com.mycompany.tiralabra_maven.totuusfunktiot;

import com.mycompany.tiralabra_maven.rajapinnat.Totuusfunktio;

/**
 * Tämän luokan <tt>tayttaa</tt>-metodi palauttaa arvon <i>true</i> jos ja vain
 * jos ensimmäisen syötteenä olevan <b>Object</b>-instanssin 
 * <tt>equals</tt>-metodi palauttaa arvon <i>true</i> kun parametrina on jokin
 * syötteen toinen <b>Object</b>.
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 * @see Totuusfunktio
 */
public class Disjunktio implements Totuusfunktio {

    @Override
    public boolean tayttaa(final Object... SYOTE) {
        // Pitää miettiä miten tätä ehtoa voisikaan soveltaa automaatissa
        // sillä jokaisella Automaattisolmulla on yksi tila, yksi seuraajatila
        // sekä jokaisessa syötteessä on yksi Object...
        Object vertailtava = SYOTE[0], vaihtoehto;
        for (int i = 1; i < SYOTE.length; i++) {
            if (vertailtava.equals(SYOTE[i])) {
                return true;
            }
        }
        return false;
    }
    
}
