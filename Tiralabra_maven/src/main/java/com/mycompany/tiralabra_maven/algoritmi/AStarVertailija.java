/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven.algoritmi;



import com.mycompany.tiralabra_maven.Koordinaatit;
import java.util.Comparator;

/**
 * Comparator-rajapinnan toteuttava luokka jota käytetään solmujen vertailemiseen.
 * @author mikko
 */
public class AStarVertailija implements Comparator<Solmu>{
    private Heuristiikka heuristiikka;
    private Koordinaatit maali;
    
    /**
     * Konstruktorissa annetaan vertailussa käytettävä heuristiikka ja maalin koordinaatit.
     * @param heuristiikka 
     * @param maali 
     */
    public AStarVertailija(Heuristiikka heuristiikka, Koordinaatit maali) {
        this.heuristiikka = heuristiikka;
        this.maali = maali;
    }

    /**
     * Järjestää solmut A*-haussa käytetyllä tavalla, joka suosii solmuja, joihin jo kuljetun matkan ja arvioidun etäisyyden maaliin summa on mahdollisimman pieni.
     * @param s1
     * @param s2
     * @return vertailun tulos
     */
    @Override
    public int compare(Solmu s1, Solmu s2) {
        if (s1.getKuljettuMatka()+heuristiikka.arvioiMatkaMaaliin(s1.getKoord(), maali) < s2.getKuljettuMatka()+heuristiikka.arvioiMatkaMaaliin(s2.getKoord(), maali)) {
            return -1;
        }
        return 1;
    }
    
}
