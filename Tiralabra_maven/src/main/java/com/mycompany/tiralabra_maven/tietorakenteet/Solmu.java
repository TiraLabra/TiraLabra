package com.mycompany.tiralabra_maven.tietorakenteet;

import com.mycompany.tiralabra_maven.peli.Siirto;

/**
 * Luokka sisältää metodeja pelipuun solmujen käsittelyyn
 * @author noora
 */
public class Solmu {
    private final int arvo;
    private final Siirto siirto;
    
    public Solmu(int arvo, Siirto siirto){
        this.arvo = arvo;
        this.siirto = siirto;
    }

    public int getArvo() {
        return arvo;
    }

    public Siirto getSiirto() {
        return siirto;
    }
    
    
}
