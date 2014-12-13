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
 * AStar-haun käyttämä laskin. Laskee kaarien kulkemisen kustannuksen verkossa 
 * ja arvioi jäljelläolevaa matkaa.
 * 
 * @author E
 */
public interface Laskin {
    /**
     * Seuraavan polun vaiheen laskeminen
     * 
     * @param current Polku, jota pitkin uuteen tullaan
     * @param kuljettuKaari Kaari, jota pitkin edellisestä solmusta tullaan tähän
     * @param seuraava Seuraava solmu
     * @param maali Kohdesolmu
     * @return 
     */
    public Node laskeSeuraava( Node current, Edge kuljettuKaari, Value seuraava, Value maali );
    /**
     * Asetetaan laskimelle verkko
     * 
     * @param verkko 
     */
    public void setVerkko( Graph verkko );
}
