package com.mycompany.tiralabra_maven.logiikka.algoritmi;

import com.mycompany.tiralabra_maven.AlgoritmiTyyppi;
import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.gui.Ruutu;
import com.mycompany.tiralabra_maven.logiikka.algoritmi.heuristiikka.Heuristiikka;

/**
 * Tehdasluokka, joka osaa tehtä eri tyyppisiä algoritmeja.
 * @author mikko
 */
public class AlgoritmiTehdas {

    /**
     * Palauttaa algoritmin annettujen parametrien perusteella.
     *
     * @param tyyppi Algoritmin tyyppi.
     * @param maailma Algoritmin toimintaympäristö
     * @param hidaste Hidaste millisekunteina
     * @param alkuKoord Alkupisteen koordinaatit
     * @param maaliKoord Maalin koordinaatit
     * @param vinottain Onko vinottain liikkuminen sallittua
     * @param kaytettavaHeuristiikka Käytettävä heuristiikka. Jos algoritmi on
     * tyyppinsä perusteella ei-heuristinen, tämän kentän arvoa ei käytetä.
     * @return
     */
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
