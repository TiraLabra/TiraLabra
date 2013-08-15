
package com.mycompany.tiralabra_maven.automaatit;

import com.mycompany.tiralabra_maven.rajapinnat.Totuusfunktio;

/**
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public class Automaattisolmu {
    // Piti tehdä tämä luokka testausta varten publiciksi vaikka sen on
    // tarkoitus olla Automaatin komponentti.
    
    final int               INDEKSI;
    private final Totuusfunktio  EHTO;
    private final Object    TILA;
    private Automaattisolmu seuraaja;
    
    public Automaattisolmu(
            final int           INDEKSI,
            final Totuusfunktio EHTO,
            final Object        TILA) {
        this.INDEKSI    = INDEKSI;
        this.EHTO       = EHTO;
        this.TILA       = TILA;
    }
    
    public void asetaSeuraaja(final Automaattisolmu SEURAAJA) {
        this.seuraaja = SEURAAJA;
    }
    
    public Automaattisolmu tilasiirtyma(final Object SYOTE) {
        if (EHTO.tayttaa(TILA, SYOTE)) {
            return seuraaja;
        }
        return null;
    }
    
}
