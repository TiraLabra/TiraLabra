/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

import java.util.ArrayList;

/**
 *
 * @author iivo
 */
public class Verkko {
    private ArrayList<Solmu> solmut;

    public Verkko() {
        solmut = new ArrayList<Solmu>();
    }
    
    public void lisaaSolmu(Solmu solmu) {
        solmut.add(solmu);
    }

    public ArrayList<Solmu> getSolmut() {
        return solmut;
    }
}
