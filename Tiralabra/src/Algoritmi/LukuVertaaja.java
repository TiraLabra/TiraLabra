/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algoritmi;

import java.util.Comparator;

/**
 * @author Jaakko
 * 
 * Vertaa kahden solmun heurestiikka-arvoja.
 */
public class LukuVertaaja implements Comparator<Solmu> {

    public LukuVertaaja() {
    }

    @Override
    public int compare(Solmu x, Solmu y) {
        
        if (x.Heurestiikaarvo < y.Heurestiikaarvo){
            return -1;
        }
        
        if (x.Heurestiikaarvo > y.Heurestiikaarvo){
            return 1;
        }
        
        return 0;
        
    }

}
