package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.Siirto;

/**
 * Pelaajaa kuvaava rajapinta, jolta voidaan kysyä seuraavaa siirtoa pelissä.
 * @author noora
 */
public interface Pelaaja {
    
    /**
     * Palauttaa seuraavan siirron
     * @param sallitutSiirrot lista sallituista siirroista
     * @return seuraava siirto
     */
    public Siirto seuraavaSiirto(Siirto[] sallitutSiirrot);
}
