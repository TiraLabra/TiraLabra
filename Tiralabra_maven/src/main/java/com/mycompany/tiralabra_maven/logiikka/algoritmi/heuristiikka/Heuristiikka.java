/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.logiikka.algoritmi.heuristiikka;

import com.mycompany.tiralabra_maven.Koordinaatit;

/**
 * Heuristiikka arvioi etäisyyden kahden pisteen välillä.
 * @author mikko
 */
public interface Heuristiikka {
    /**
     * Arvioi parametreinä annettujen koordinaattien etäisyyden maaliin.
     * @param koordinaatit
     * @param maali
     * @return arvioitu etäisyys maaliin
     */
    public abstract double arvioiMatkaMaaliin(Koordinaatit koordinaatit, Koordinaatit maali);
}
