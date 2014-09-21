package com.mycompany.tiralabra_maven;

/**
* Pinosolmu on linkitetyn listan alkio. Se sisältää solmu-olion ja viitteen
* seuraavaan pinosolmuun.
* 
*/
public class Pinosolmu {
    private Solmu sisalto;
    private Pinosolmu seuraava;

    public Solmu getSisalto() {
        return sisalto;
    }

    public void setSisalto(Solmu sisalto) {
        this.sisalto = sisalto;
    }

    public Pinosolmu getSeuraava() {
        return seuraava;
    }

    public void setSeuraava(Pinosolmu seuraava) {
        this.seuraava = seuraava;
    }
    
    
}
