/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven.domain;

/**
 *
 * @author szetk
 */
public class Laatikko{
    
    private Laatikkotyyppi tyyppi;
    private Sijainti sijainti;
    private int orientaatio;

    public Laatikko(Laatikkotyyppi tyyppi, Sijainti sijainti, int orientaatio){
        this.tyyppi = tyyppi;
        this.sijainti = sijainti;
        this.orientaatio = orientaatio;
    }

    public Laatikkotyyppi getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(Laatikkotyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }

    public Sijainti getSijainti() {
        return sijainti;
    }

    public void setSijainti(Sijainti sijainti) {
        this.sijainti = sijainti;
    }

    public int getOrientaatio() {
        return orientaatio;
    }

    public void setOrientaatio(int orientaatio) {
        this.orientaatio = orientaatio;
    }
    
    
    
}
