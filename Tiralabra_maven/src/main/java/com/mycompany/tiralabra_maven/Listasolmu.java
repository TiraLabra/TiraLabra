package com.mycompany.tiralabra_maven;

/**
* Listasolmu on linkitetyn listan alkio. Se sisältää viitteet solmu-olioon ja
* seuraavaan pinosolmuun.
* 
* Kaikkien metodit ovat vakioaikaisia.
* 
*/
public class Listasolmu {
    private Solmu sisalto;
    private Listasolmu seuraava;

    public Solmu getSisalto() {
        return sisalto;
    }

    public void setSisalto(Solmu sisalto) {
        this.sisalto = sisalto;
    }

    public Listasolmu getSeuraava() {
        return seuraava;
    }

    public void setSeuraava(Listasolmu seuraava) {
        this.seuraava = seuraava;
    }
    
    
}
