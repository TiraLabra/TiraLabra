
package com.mycompany.tiralabra_maven.automaatit;

import com.mycompany.tiralabra_maven.rajapinnat.Totuusfunktio;

/**
 * Tämän luokan tarkoituksena on toimia osana determinististä äärellistä
 * automaattia. <b>Automaattisolmu</b> sisältää yhden tilamuuttujan johon se
 * vertaa syötteenä annettua muuttujaa <b>Totuusfunktio</b>-instanssin avulla.
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public class Automaattisolmu {
    // Pitäisi varmaan vähän tsekata tätä terminologiaa...
    
    final int               INDEKSI; // Tämän muuttujan tarkoituksena on kertoa
                                     // Automaatille monesko syötteen merkki
                                     // tälle solmulle annetaan käsittelyyn.
                                     // Tämän voisi varmaan toteuttaa fiksummin.
    private final Totuusfunktio  EHTO;
    private final Object    TILA;
    private Automaattisolmu seuraaja;
    
    Automaattisolmu(
            final int           INDEKSI,
            final Totuusfunktio EHTO,
            final Object        TILA) {
        this.INDEKSI    = INDEKSI;
        this.EHTO       = EHTO;
        this.TILA       = TILA;
    }
    
    void asetaSeuraaja(final Automaattisolmu SEURAAJA) {
        this.seuraaja = SEURAAJA;
    }
    
    /**
     * Palauttaa seuraavan <b>Automaaattisolmu</b>-instanssin jos ja vain jos 
     * syöte toteuttaa tilasiirtymälle annetun ehdon. Muussa tapauksessa metodi
     * palauttaa arvon <i>null</i>.
     * 
     * @param SYOTE Vertailtava <b>Object</b>.
     * @return Seuraava <b>Automaattisolmu</b> tai <i>null</i>.
     * @see Totuusfunktio
     */
    Automaattisolmu tilasiirtyma(final Object SYOTE) {
        if (EHTO.tayttaa(TILA, SYOTE)) {
            return seuraaja;
        }
        return null;
    }
    
}
