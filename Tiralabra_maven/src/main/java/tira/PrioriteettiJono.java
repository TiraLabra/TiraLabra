/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

import java.util.Comparator;

/**
 * A*-haku käyttää prioriteettijonoa solmujen käsittelyjärjestyksessä
 * 
 * 
 * @author E
 * @param <E> Jonon tyyppi
 */
public class PrioriteettiJono<E> {
    private Comparator<E> vertailija;
    /*
    TOTEUTUS: minimikeko, fibonacci-keko yms yms
    */
    
    
    
    /**
     * Lisätään oikealle paikalle arvo
     * Tavoite O(log n)...O(1)
     * 
     * @param e Lisättävä arvo
     */
    public void add( E e ) {
        // WIP
        
        // oikea paikka arvolle etsitään käyttämällä vertailijaa
        // tehokas järjestely tähän
    }
    /**
     * Poistetaan ja palautetaan korkeimman prioriteetin arvo
     * Tavoite O(log n)
     * 
     * @return 
     */
    public E poll() {
        // WIP
        return null;
    }
    
    public boolean isEmpty() {
        // WIP
        return false;
    }
    
    // automaattiset setterit&getterit

    public Comparator<E> getVertailija() {
        return vertailija;
    }

    public void setVertailija(Comparator<E> vertailija) {
        this.vertailija = vertailija;
    }
    
}
