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
public class Laatikkotyyppi extends Sarmio{
    private ArrayList<Laatikko> laatikot;
    
    public Laatikkotyyppi(int x, int y, int z) {
        super(x, y, z);
        this.laatikot = new ArrayList<Laatikko>();
    }
    
    public Laatikkotyyppi(int x, int y, int z, ArrayList<Laatikko> laatikot) {
        super(x, y, z);
        this.laatikot = laatikot;
    }

    public ArrayList<Laatikko> getLaatikot() {
        return laatikot;
    }

    public void setLaatikot(ArrayList<Laatikko> laatikot) {
        this.laatikot = laatikot;
    }
    
    
}
