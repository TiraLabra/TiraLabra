/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.logiikka.algoritmi;

import com.mycompany.tiralabra_maven.AlgoritmiTyyppi;
import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.gui.Ruutu;
import com.mycompany.tiralabra_maven.logiikka.algoritmi.heuristiikka.Heuristiikka;

/**
 *
 * @author mikko
 */
public class AlgoritmiTehdas {

    public Algoritmi luoAlgoritmi(AlgoritmiTyyppi tyyppi, Ruutu[][] maailma, int hidaste, Koordinaatit alkuKoord, Koordinaatit maaliKoord, boolean vinottain, Heuristiikka kaytettavaHeuristiikka) {
        switch (tyyppi) {
            case A_STAR:
                return new AStarAlgoritmi(maailma, hidaste, alkuKoord, maaliKoord, vinottain, kaytettavaHeuristiikka);
            case BREADTH_FIRST:
                return new BreadthFirstAlgoritmi(maailma, hidaste, alkuKoord, maaliKoord, vinottain);
            case DIJKSTRA:
                return new DijkstraAlgoritmi(maailma, hidaste, alkuKoord, maaliKoord, vinottain);
            case GREEDY_BEST_FIRST:
                return new GreedyBestFirstAlgoritmi(maailma, hidaste, alkuKoord, maaliKoord, vinottain, kaytettavaHeuristiikka);
        }
        return null;
    }
}
