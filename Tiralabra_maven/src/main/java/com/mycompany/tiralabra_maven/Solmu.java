/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

/**
 * Luokka kuvaa yhtä solmua linkitetyllä listalle. Solmulla on avain, arvo ja 
 * linkit seuraavaan ja edelliseen solmuun.
 * 
 * @author sampox
 */
public class Solmu {
    /**
 * Solmun avaimen arvo
 * 
 */
    private String avain;
    /**
 * Solmun arvo
 * 
 */
    private String arvo;
    /**
 * Solmun jälkeen listalla oleva solmu
 * 
 */
    private Solmu seuraava;
    /**
 * Solmua edeltävä solmu listalla
 * 
 */
    private Solmu edellinen;
    
    /**
 * Konstruktori joka luo uuden solmun.
 * 
 * @param avain uuden solmun avaimen arvo
 * @param arvo uuden solmun arvo
 * @param seuraava listalla seuraavana oleva solmu
 * @param edellinen listalla edellisenä oleva solmu
 * 
 */
public Solmu(String avain, String arvo, Solmu seuraava, Solmu edellinen) {
        this.avain = avain;
        this.arvo = arvo;
        this.seuraava = seuraava;
        this.edellinen = edellinen;
}
    
public String getArvo() {
        return arvo;
    }

    public void setArvo(String arvo) {
        this.arvo = arvo;
    }
   
    
    public String getAvain() {
        return avain;
    }

    public void setAvain(String avain) {
        this.avain = avain;
    }


    public Solmu getEdellinen() {
        return edellinen;
    }

    public void setEdellinen(Solmu edellinen) {
        this.edellinen = edellinen;
    }

    public Solmu getSeuraava() {
        return seuraava;
    }

    public void setSeuraava(Solmu seuraava) {
        this.seuraava = seuraava;
    }
}
