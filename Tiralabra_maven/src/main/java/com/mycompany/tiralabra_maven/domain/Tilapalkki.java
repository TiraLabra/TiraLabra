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
public class Tilapalkki extends Sarmio{
    
    private Sijainti sijainti;

    public Tilapalkki(int x, int y, int z, int posX, int posY, int posZ) {
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
