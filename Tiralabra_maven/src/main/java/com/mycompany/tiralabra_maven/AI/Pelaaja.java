package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.Peli;
import com.mycompany.tiralabra_maven.Siirto;

/**
 * Abstrakti luokka, joka toimii tekoälynä eli jolta voi kysyä seuraavaa siirtoa
 * @author noora
 */
public abstract class Pelaaja {
    protected Peli peli;
    
    public Pelaaja(Peli peli){
        this.peli = peli;
    }
    
    /**
     * Metodi palauttaa seuraavan siirron
     * @param sallitutSiirrot
     * @return paluttaa seuraavan siirron
     */
    public abstract Siirto seuraavaSiirto(Siirto[] sallitutSiirrot);
}
