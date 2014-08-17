/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven.algoritmi;



import java.util.Comparator;

/**
 * Comparator-rajapinnan toteuttava luokka jota käytetään solmujen vertailemiseen.
 * @author mikko
 */
public class Vertailija implements Comparator<Solmu>{
    private Heuristiikka heuristiikka;
    
    /**
     * Konstruktorissa annetaan käytettävä heuristiikka.
     * @param heuristiikka 
     */
    public Vertailija(Heuristiikka heuristiikka) {
        this.heuristiikka = heuristiikka;
    }

    /**
     * Järjestää solmut A*-haussa käytetyllä tavalla, joka suosii solmuja, joihin jo kuljetun matkan ja arvioidun etäisyyden maaliin summa on mahdollisimman pieni.
     * @param s1
     * @param s2
     * @return vertailun tulos
     */
    @Override
    public int compare(Solmu s1, Solmu s2) {
        if (s1.getKuljettuMatka()+heuristiikka.arvioiMatkaMaaliin(s1.getKoordinaatit()) < s2.getKuljettuMatka()+heuristiikka.arvioiMatkaMaaliin(s2.getKoordinaatit())) {
            return -1;
        }
        return 1;
    }
    
}
