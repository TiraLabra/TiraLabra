/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.logiikka.algoritmi;

import com.mycompany.tiralabra_maven.Koordinaatit;

/**
 * Heuristiikka-luokan perivä luokka, joka arvioi matkan maaliin käyttäen
 * manhattan-etäisyyttä, eli x- ja y-koordinaattien erotusten itseisarvojen
 * summaa.
 *
 * @author mikko
 */
public class ManhattanHeuristiikka extends Heuristiikka {

    /**
     * Konstruktorissa annetaan maalin koordinaatit.
     *
     */
    public ManhattanHeuristiikka() {
        super();
    }

    /**
     * Palauttaa manhattan-etäisyyden parametrina annettujen koordinaattien ja
     * maalin välille.
     *
     * @param koord
     * @return arvioitu matka maaliin
     */
    @Override
    public int arvioiMatkaMaaliin(Koordinaatit koord, Koordinaatit maali) {
        return Math.abs(koord.getX() - maali.getX()) + Math.abs(koord.getY() - maali.getY());
    }

}
