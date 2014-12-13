/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verkko.rajapinnat;

import verkko.Reitti;

/**
 * Verkko.
 * 
 * @author E
 */
public interface Graph {
    /**
     * Solmusta alku solmuun loppu johtavat kaaret
     * 
     * @param alku Alkusolmu
     * @param loppu Loppusolmu
     * @return 
     */
    Iterable<Edge> getKaaret( Value alku, Value loppu );
    /**
     * Solmun naapurit
     * 
     * @param alku Alkusolmu
     * @return Solmut, joihin alkusolmusta on kaari
     */
    Iterable<Value> getNaapurit( Value alku );
    
    /**
     * Palauttaa uuden tyhjän olion. Käytetään reittihaussa.
     * 
     * @return Uusi tyhjä Node-olio
     */
    Node getNode();
}


