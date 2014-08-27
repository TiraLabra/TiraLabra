package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.Peli;
import com.mycompany.tiralabra_maven.PeliOhjain;
import com.mycompany.tiralabra_maven.Siirto;

/**
 * Luokka toimii yksinkertaisena tekoälynä
 * @author noora
 */
public class EkaAI extends AI {
    
    public EkaAI(Peli peli, PeliOhjain peliohjain, boolean siirraAutomaagisesti, int viive){
        super(peli, peliohjain, siirraAutomaagisesti, viive);
    }
    

    /**
     * Metodi palauttaa seuraavan siirron, joka tässä tapauksessa on aina mahdollisten siirtojen listan ensimmäinen siirto
     * @return Palauttaa seuraavan siirron
     */
    @Override
    public Siirto seuraavaSiirto(Siirto[] sallitutSiirrot) {
        odota();
        return sallitutSiirrot[0];
    }
    
}
