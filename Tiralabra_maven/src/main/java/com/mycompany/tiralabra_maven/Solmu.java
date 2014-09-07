/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

/**
 * Luokka kuvaa yhtä solmua linkitetyllä listalle. Solmulla on merkintä ja 
 * linkit seuraavaan ja edelliseen solmuun.
 * 
 * @author sampox
 */
public class Solmu {
    /**
 * Solmun merkinta, avain+arvo pari
 * 
 */
    private TaulunMerkinta merkinta;
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
		this.merkinta = new TaulunMerkinta(avain,arvo);
        this.seuraava = seuraava;
        this.edellinen = edellinen;
}
    

    public TaulunMerkinta getMerkinta() {
		return merkinta;
	}


	public void setMerkinta(TaulunMerkinta merkinta) {
		this.merkinta = merkinta;
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
