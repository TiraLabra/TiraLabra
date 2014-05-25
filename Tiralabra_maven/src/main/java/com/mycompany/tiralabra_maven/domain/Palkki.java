/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven.domain;

import java.util.HashMap;

/**
 *
 * @author szetk
 */
public class Palkki extends Sarmio{
    
    private HashMap<Laatikko, Sijainti> laatikot;
    private Sijainti sijainti;

    public Palkki(int x, int y, int z, HashMap<Laatikko, Sijainti> laatikot) {
        super(x, y, z);
        this.laatikot = laatikot;
    }
    
    public Palkki(int x, int y, int z) {
        super(x, y, z);
//        this.sijainti = new Sijainti(0, 0, 0);
    }

    public HashMap<Laatikko, Sijainti> getLaatikot() {
        return laatikot;
    }

    public void setLaatikot(HashMap<Laatikko, Sijainti> laatikot) {
        this.laatikot = laatikot;
    }

    public Sijainti getSijainti() {
        return sijainti;
    }

    public void setSijainti(Sijainti sijainti) {
        this.sijainti = sijainti;
    }
    
    
    
}
