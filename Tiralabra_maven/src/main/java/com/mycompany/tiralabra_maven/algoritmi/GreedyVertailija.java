/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.algoritmi;

import com.mycompany.tiralabra_maven.Koordinaatit;
import java.util.Comparator;

/**
 *
 * @author mikko
 */
public class GreedyVertailija implements Comparator<Solmu> {

    private Heuristiikka heuristiikka;
    private Koordinaatit maali;

    /**
     * Konstruktorissa annetaan vertailussa käytettävä heuristiikka ja maalin
     * koordinaatit.
     *
     * @param heuristiikka
     * @param maali
     */
    public GreedyVertailija(Heuristiikka heuristiikka, Koordinaatit maali) {
        this.heuristiikka = heuristiikka;
        this.maali = maali;
    }

    /**
     * Järjestää solmut Greedy Best First-haussa käytetyllä tavalla, joka suosii
     * solmuja, joista arvioitu etäisyys maaliin on mahdollisimman pieni.
     *
     * @param s1
     * @param s2
     * @return vertailun tulos
     */
    @Override
    public int compare(Solmu s1, Solmu s2) {
        return heuristiikka.arvioiMatkaMaaliin(s1.getKoord(), maali) - heuristiikka.arvioiMatkaMaaliin(s2.getKoord(), maali);
    }

}
