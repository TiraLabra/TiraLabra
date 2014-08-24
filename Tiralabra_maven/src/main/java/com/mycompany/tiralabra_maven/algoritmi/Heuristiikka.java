/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.algoritmi;

import com.mycompany.tiralabra_maven.Koordinaatit;

/**
 * Abstrakti luokka, jolle annetaan maalin koordinaatit, ja jolta voidaan kysyä
 * eri koordinaattien arvioituja etäisyyksiä maalikoordinaatteihin.
 *
 * @author mikko
 */
public abstract class Heuristiikka {

    
    /**
     * Konstruktorissa annetaan maalin koordinaatit.
     */

    public Heuristiikka() {

    }

    /**
     * Arvioi parametreinä annettujen koordinaattien etäisyyden maaliin.
     * @param koordinaatit
     * @return arvioitu etäisyys maaliin
     */
    
    public abstract int arvioiMatkaMaaliin(Koordinaatit koordinaatit, Koordinaatit maali);
}
