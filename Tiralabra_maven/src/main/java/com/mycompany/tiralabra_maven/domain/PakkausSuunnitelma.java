/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.domain;

import java.util.ArrayList;

/**
 *
 * @author szetk
 */
public class PakkausSuunnitelma {

    private ArrayList<Palkki> palkit;

    public PakkausSuunnitelma(ArrayList<Palkki> palkit) {
        this.palkit = palkit;
    }

    public PakkausSuunnitelma() {
        this.palkit = new ArrayList<Palkki>();
    }

    public ArrayList<Palkki> getPalkit() {
        return this.palkit;
    }

    public void setPalkit(ArrayList<Palkki> palkit) {
        this.palkit = palkit;
    }

    
}
