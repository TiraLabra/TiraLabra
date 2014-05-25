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
public class PakkausSuunnitelma {
    
    private HashMap<Laatikko, Sijainti> laatikot;

    public PakkausSuunnitelma(HashMap<Laatikko, Sijainti> laatikot) {
        this.laatikot = laatikot;
    }

    public PakkausSuunnitelma() {
        this.laatikot = new HashMap<Laatikko, Sijainti>();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public HashMap<Laatikko, Sijainti> getLaatikot() {
        return laatikot;
    }

    public void setLaatikot(HashMap<Laatikko, Sijainti> laatikot) {
        this.laatikot = laatikot;
    }
    
    
}
