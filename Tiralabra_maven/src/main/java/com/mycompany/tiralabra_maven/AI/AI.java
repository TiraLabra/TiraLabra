package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.Peli;
import com.mycompany.tiralabra_maven.Siirto;

/**
 * Abstrakti luokka, joka toimii tekoälynä eli jolta voi kysyä seuraavaa siirtoa
 * @author noora
 */
public abstract class AI {
    protected Peli peli;
    
    public AI(Peli peli){
        this.peli = peli;
    }
    
    /**
     * Metodi palauttaa seuraavan siirron
     * @return paluttaa seuraavan siirron
     */
    public abstract Siirto seuraavaSiirto();
}
