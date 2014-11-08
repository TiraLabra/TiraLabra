/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

/**
 * A*-haku käyttää prioriteettijonoa solmujen käsittelyjärjestyksessä
 * 
 * 
 * @author E
 * @param <E> Jonon jäsenien tyyppi
 */
public class PrioriteettiJono<E> {
    /**
     * Jäsenet oikeaan järjestykseen vertailija-oliolla
     */
    private Vertailija<E> vertailija;
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
    /**
     * Tarkistaa, onko jonossa vielä jäseniä
     * 
     * @return true, jos tyhjä
     */
    public boolean isEmpty() {
        // WIP
        return false;
    }
    
    // automaattiset setterit&getterit

    public Vertailija<E> getVertailija() {
        return vertailija;
    }

    public void setVertailija(Vertailija<E> vertailija) {
        this.vertailija = vertailija;
    }
    
}
