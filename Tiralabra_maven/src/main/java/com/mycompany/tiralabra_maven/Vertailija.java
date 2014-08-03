/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

import java.util.Comparator;

/**
 *
 * @author mikko
 */
public class Vertailija implements Comparator<Solmu>{
    private Koordinaatit maali;
    
    public Vertailija(Koordinaatit maali) {
        this.maali = maali;
    }

    public int compare(Solmu s1, Solmu s2) {
        if (s1.getKuljettuMatka()+heuristiikka(s1.getKoordinaatit()) < s2.getKuljettuMatka()+heuristiikka(s2.getKoordinaatit())) {
            return -1;
        }
        return 1;
    }
    
    private int heuristiikka(Koordinaatit koord) {
        return Math.abs(koord.getX()-maali.getX()) + Math.abs(koord.getY()-maali.getY()); // Manhattan
        //return (int)(Math.floor(Math.sqrt((koord.getX()-maali.getX())*(koord.getX()-maali.getX())+(koord.getY()-maali.getY())*(koord.getY()-maali.getY()))));
        
    }


    
}
