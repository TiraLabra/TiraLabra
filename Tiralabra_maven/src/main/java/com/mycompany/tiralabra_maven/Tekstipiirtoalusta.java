package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.gui.Paivitettava;

/**
 *
 * @author noora
 */
public class Tekstipiirtoalusta implements Paivitettava {
    
    public Tekstipiirtoalusta(){
        
    }

    @Override
    public void paivita() {
    }

    @Override
    public void naytaViesti(String viesti) {
        System.out.println(viesti);
        
    }
    
}
