/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;


/**
 * Luokka toteuttaa hajautustaulun käyttäen apuna linkitettyä listaa.
 *
 * @author sampox
 */
public class HajautustauluLinkitetyllaListalla {

    /**
     * Taulukko johon alkiot/alkiolistat tallennetaan
     *
     */
    private KahteenSuuntaanLinkitettyLista[] taulukko;
    /**
     * Hajautustaulukon koko
     *
     */
    private int taulukonKoko;
    /**
     * Merkintöjen määrä hajautustaulussa;
     *
     */
    private int merkintoja;

public HajautustauluLinkitetyllaListalla() {
	this.merkintoja=0;
	this.taulukonKoko=16;
	this.taulukko = new KahteenSuuntaanLinkitettyLista[taulukonKoko];
}
}
