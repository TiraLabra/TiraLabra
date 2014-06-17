/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven.domain;

/**
 * Tämä kuvaa suorakulmaisen särmiön muotoista tyhjää tilaa kontissa
 * @author szetk
 */
public class Tilapalkki extends Sarmio{
    
    private Sijainti sijainti;
/**
 * Konstruktori
 * @param x Tilapalkin pituus
 * @param y Tilapalkin leveys
 * @param z Tilapalkin korkeus
 */
    public Tilapalkki(long x, long y, long z) {
        super(x, y, z);	
        this.sijainti = null;
    }

    public Tilapalkki(long x, long y, long z, long posX, long posY, long posZ) {
        super(x, y, z);
        this.sijainti = new Sijainti(posX, posY, posZ);
    }   

    public Sijainti getSijainti() {
        return sijainti;
    }

    public void setSijainti(Sijainti sijainti) {
        this.sijainti = sijainti;
    }
    
    
    
}
