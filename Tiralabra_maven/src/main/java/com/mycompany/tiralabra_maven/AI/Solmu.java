package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.Siirto;

/**
 * Luokka sisältää metodeja pelipuun solmujen käsittelyyn
 * @author noora
 */
public class Solmu {
    private Solmu vanhempi;
    private Solmu[] lapset;
    private int arvo;
    private boolean onkoValkoisenVuoro;
    private Siirto siirto;
    
    public Solmu(Solmu vanhempi, boolean onkoValkoisenVuoro, Siirto siirto){
        this.vanhempi = vanhempi;
        this.onkoValkoisenVuoro = onkoValkoisenVuoro;
        this.siirto = siirto;
        this.lapset = new Solmu[1000];
    }

    public Solmu getVanhempi() {
        return vanhempi;
    }

    public Solmu[] getLapset() {
        return lapset;
    }

    public int getArvo() {
        return arvo;
    }

    public boolean isOnkoValkoisenVuoro() {
        return onkoValkoisenVuoro;
    }

    public Siirto getSiirto() {
        return siirto;
    }
    
    
}
