
package com.mycompany.tiralabra_maven.totuusfunktiot;

import com.mycompany.tiralabra_maven.rajapinnat.Totuusfunktio;

/**
 * Tämä luokka mallintaa olioiden semanttista identiteettiä. Metodi <tt>tayttaa</tt>
 * palauttaa arvon <i>true</i> jos ja vain jos syötteen ensimmäisen
 * <b>Object</b>-instanssin <tt>equals</tt>-metodi palauttaa arvon true
 * parametrin ollessa viite syötteen toiseen <b>Object</b>:iin.
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public class Identiteetti implements Totuusfunktio {

    /**
     * Tutkii onko syötteen (kahdella ensimmäisellä) olioilla sama tietosisältö.
     * 
     * @param SYOTE
     * @return 
     */
    public boolean tayttaa(Object... SYOTE) {
        return SYOTE[0].equals(SYOTE[1]);
    }
    
}
