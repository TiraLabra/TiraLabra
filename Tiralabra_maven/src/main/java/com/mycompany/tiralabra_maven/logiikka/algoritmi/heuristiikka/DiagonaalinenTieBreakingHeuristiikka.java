/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven.logiikka.algoritmi.heuristiikka;

import com.mycompany.tiralabra_maven.Koordinaatit;

/**
 *
 * @author mikko
 */
public class DiagonaalinenTieBreakingHeuristiikka implements Heuristiikka {

    @Override
    public double arvioiMatkaMaaliin(Koordinaatit koordinaatit, Koordinaatit maali) {
        return Math.max(Math.abs(koordinaatit.getX() - maali.getX()), Math.abs(koordinaatit.getY() - maali.getY()))*1.001;
    }
    
}
