package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.Peli;
import com.mycompany.tiralabra_maven.Siirto;

/**
 * Luokka toimii yksinkertaisena tekoälynä
 * @author noora
 */
public class EkaAI extends Pelaaja {
    
    public EkaAI(Peli peli){
        super(peli);
    }
    

    /**
     * Metodi palauttaa seuraavan siirron, joka tässä tapauksessa on aina mahdollisten siirtojen listan ensimmäinen siirto
     * @return Palauttaa seuraavan siirron
     */
    @Override
    public Siirto seuraavaSiirto(Siirto[] sallitutSiirrot) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        
        }
        return sallitutSiirrot[0];
    }
    
}
