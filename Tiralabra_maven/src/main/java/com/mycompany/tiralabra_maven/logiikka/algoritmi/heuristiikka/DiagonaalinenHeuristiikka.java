/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.logiikka.algoritmi.heuristiikka;

import com.mycompany.tiralabra_maven.Koordinaatit;

/**
 *
 * @author mikko
 */
public class DiagonaalinenHeuristiikka implements Heuristiikka {

    /**
     * Liikkuminen vinottain maksaa saman verran kuin liikkuminen
     * vaakasuunnassa, joten yhdellä "liikkumisella" voidaan liikkua yksi ruutu
     * pystysuunnassa ja yksi ruutu leveyssuunnassa. Diagonaalisessa
     * heuristiikassa otetaan tämä huomioon ja palautetaan joko x-koordinaattien
     * erotuksen itseisarvo tai y-koordinaattien erotuksen itseisarvo riippuen
     * siitä, kumpi on suurempi (eli arvioidussa jäljellä olevassa matkassa
     * "pullonkaula") .
     *
     * @param koordinaatit
     * @param maali
     * @return
     */
    @Override
    public double arvioiMatkaMaaliin(Koordinaatit koordinaatit, Koordinaatit maali) {
        return Math.max(Math.abs(koordinaatit.getX() - maali.getX()), Math.abs(koordinaatit.getY() - maali.getY()));
    }

}
