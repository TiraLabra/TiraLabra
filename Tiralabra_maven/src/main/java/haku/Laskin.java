/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haku;

import verkko.rajapinnat.Edge;
import verkko.rajapinnat.Graph;
import verkko.rajapinnat.Node;
import verkko.rajapinnat.Value;

/**
 *
 * @author E
 */
public interface Laskin {
    /*
    double heuristinenArvio( Value current, Value maali ) {
        return current.etaisyys( maali );
    }
    */
    public Node laskeSeuraava( Node current, Edge kuljettuKaari, Value seuraava, Value maali ); /* {
        Node uusi = null;  // = new Tila();
        
        uusi.setArvioituKustannus(seuraava.etaisyys(maali) );
        uusi.setKustannus( current.getKustannus() + kuljettuKaari.getKustannus() );
        uusi.setPrevious(current);
        uusi.setKuljettuKaari(kuljettuKaari);
        uusi.setSolmu(seuraava);
        
        return uusi;
    }
    */
    public void setVerkko( Graph verkko );
}
