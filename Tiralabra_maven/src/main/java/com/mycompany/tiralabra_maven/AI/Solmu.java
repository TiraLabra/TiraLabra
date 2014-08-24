package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.Siirto;

/**
 * Luokka sisältää metodeja pelipuun solmujen käsittelyyn
 * @author noora
 */
public class Solmu {
    private int arvo;
    private Siirto siirto;
    
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
