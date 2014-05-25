/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven.domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

/**
 *
 * @author szetk
 */
public class Tila {
    private PakkausSuunnitelma pakkausSuunnitelma;
    private int tilavuus;
    private ArrayList<Laatikko> vapaatLaatikot;
    private Deque<Tilapalkki> vapaatTilapalkit;

        
    public Tila(Kontti kontti, ArrayList<Laatikko> laatikot) {
        this.pakkausSuunnitelma = new PakkausSuunnitelma();
        this.tilavuus = 0;
        this.vapaatLaatikot = laatikot;
        this.vapaatTilapalkit = new ArrayDeque<Tilapalkki>();
        this.vapaatTilapalkit.push(new Tilapalkki(kontti.getX(),kontti.getY(),kontti.getZ(), 0, 0, 0));
    }

    public PakkausSuunnitelma getPakkausSuunnitelma() {
        return pakkausSuunnitelma;
    }

    public void setPakkausSuunnitelma(PakkausSuunnitelma pakkausSuunnitelma) {
        this.pakkausSuunnitelma = pakkausSuunnitelma;
    }

    public int getTilavuus() {
        return tilavuus;
    }

    public void setTilavuus(int tilavuus) {
        this.tilavuus = tilavuus;
    }

    public ArrayList<Laatikko> getVapaatLaatikot() {
        return vapaatLaatikot;
    }

    public void setVapaatLaatikot(ArrayList<Laatikko> vapaatLaatikot) {
        this.vapaatLaatikot = vapaatLaatikot;
    }

    public Deque<Tilapalkki> getVapaatTilapalkit() {
        return vapaatTilapalkit;
    }

    public void setVapaatTilapalkit(Deque<Tilapalkki> vapaatTilapalkit) {
        this.vapaatTilapalkit = vapaatTilapalkit;
    }

 
    
    
}
