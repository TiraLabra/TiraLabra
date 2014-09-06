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
public class ManhattanTieBreakingHeuristiikka implements Heuristiikka{
    /**
     * Palauttaa manhattan-etäisyyden parametrina annettujen koordinaattien ja
     * maalin välille.
     *
     * @param koord
     * @param maali
     * @return arvioitu matka maaliin
     */
    @Override
    public double arvioiMatkaMaaliin(Koordinaatit koord, Koordinaatit maali) {
        return (Math.abs(koord.getX() - maali.getX()) + Math.abs(koord.getY() - maali.getY()))*1.001;
    }
}
