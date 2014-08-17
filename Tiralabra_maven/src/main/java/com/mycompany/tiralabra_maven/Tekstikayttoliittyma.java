package com.mycompany.tiralabra_maven;

/**
 * 
 * @author noora
 */
public class Tekstikayttoliittyma {
    private final Peli peli;
    private final Tekstipiirtoalusta piirtoalusta;
    
    public Tekstikayttoliittyma(Peli peli){
        this.peli = peli;
        this.piirtoalusta = new Tekstipiirtoalusta();
    }

    public Tekstipiirtoalusta getPiirtoalusta() {
        return piirtoalusta;
    }
    
    
    
}
