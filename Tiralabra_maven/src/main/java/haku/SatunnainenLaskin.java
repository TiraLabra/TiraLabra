/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haku;

import verkko.rajapinnat.*;


/**
 *
 * @author E
 */
public class SatunnainenLaskin implements Laskin {
    
    private double heuristiikanPaino=1;
    private Graph verkko;
    
    public double heuristiikka( Value value, Value maali ) {
        return heuristiikanPaino*value.etaisyys(maali);
    }

    public Node laskeSeuraava(Node current, Edge kuljettuKaari, Value seuraava, Value maali) {
        // Polku polku = new Polku();

        Node node = verkko.getNode();
        
        node.setPrevious(current);
        node.setKuljettuKaari(kuljettuKaari);
        node.setSolmu(seuraava);
        if ( current!=null && kuljettuKaari!=null) node.setKustannus(current.getKustannus()+kuljettuKaari.getKustannus());
        node.setArvioituKustannus(heuristiikka(seuraava,maali));
        
        return node;
        
    }

    public void setVerkko(Graph verkko) {
        /*SatunnainenVerkko*/this.verkko = verkko;
    }
    
}
