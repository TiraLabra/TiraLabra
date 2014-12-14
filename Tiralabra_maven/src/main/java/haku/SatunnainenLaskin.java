/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haku;

import verkko.rajapinnat.*;


/**
 * Laskin SatunnainenVerkko-verkkoa varten. 
 * Sopivalla heuristiikan painolla toimii myös muilla verkoilla.
 * 
 * @author E
 */
public class SatunnainenLaskin implements Laskin {
    
    /**
     * Heuristiikan paino
     */
    private double heuristiikanPaino; //=1;
    /**
     * Verkko, jossa laskin toimii
     */
    private Graph verkko;
    
    /**
     * Oletuslaskin, heuristiikan paino == 1
     */
    public SatunnainenLaskin() {
        this(1);
    }
    /**
     * Laskin, jossa heuristiikan painon voi päättää
     * 
     * @param heuristiikanPaino 
     */
    public SatunnainenLaskin(double heuristiikanPaino) {
        this.heuristiikanPaino = heuristiikanPaino;
    }
    
    
    /**
     * Heuristinen arvio loppumatkasta maaliin
     * 
     * @param value
     * @param maali
     * @return 
     */
    public double heuristiikka( Value value, Value maali ) {
        return heuristiikanPaino*value.etaisyys(maali);
    }
    
    ///////////////////////
    // RAJAPINNAT /////////
    ///////////////////////
    
    /**
     * Muodostetaan uusi Node-olio olion current seuraajaksi
     * 
     * @param current Edellinen Node
     * @param kuljettuKaari 
     * @param seuraava
     * @param maali
     * @return 
     */
    public Node laskeSeuraava(Node current, Edge kuljettuKaari, Value seuraava, Value maali) {
        Node node = verkko.getNode(); // tyhjä polku-olio     
        node.setPrevious(current);
        node.setKuljettuKaari(kuljettuKaari);
        node.setSolmu(seuraava);
        if ( current!=null && kuljettuKaari!=null) node.setKustannus(current.getKustannus()+kuljettuKaari.getKustannus());
        node.setArvioituKustannus(heuristiikka(seuraava,maali));
        
        return node;
        
    }
    /**
     * Asetetaan laskimelle verkko
     * 
     * @param verkko 
     */
    public void setVerkko(Graph verkko) {
        this.verkko = verkko;
    }
    
}
