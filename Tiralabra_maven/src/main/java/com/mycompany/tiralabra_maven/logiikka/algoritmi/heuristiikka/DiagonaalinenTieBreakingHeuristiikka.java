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
public class DiagonaalinenTieBreakingHeuristiikka implements Heuristiikka {

    /**
     * Liikkuminen vinottain maksaa saman verran kuin liikkuminen
     * vaakasuunnassa, joten yhdellä "liikkumisella" voidaan liikkua yksi ruutu
     * pystysuunnassa ja yksi ruutu leveyssuunnassa. Diagonaalisessa
     * heuristiikassa otetaan tämä huomioon ja palautetaan joko x-koordinaattien
     * erotuksen itseisarvo tai y-koordinaattien erotuksen itseisarvo riippuen
     * siitä, kumpi on suurempi (eli arvioidussa jäljellä olevassa matkassa
     * "pullonkaula") .
     *
     * Tämä heuristiikka on lisäksi ns. "tie breaking" eli "tasapelin rikkova"
     * heuristiikka. Tämä tarkoittaa sitä että heuristiikka arvioi etäisyyden
     * aavistuksen verran yläkanttiin, jolloin reittialgoritmissa suositaan
     * niitä solmuja joissa ollaan edetty vähän maalia kohti (sen sijaan että
     * kaikkien mahdollisten eri reittien välille syntyisi tasapeli, joka
     * johtaisi siihen että algoritmi tutkisi kaikki mahdolliset keskenään yhtä
     * hyvät reittivaihtoehdot).
     *
     * @param koordinaatit
     * @param maali
     * @return arvioitu etäisyys maaliin
     */
    @Override
    public double arvioiMatkaMaaliin(Koordinaatit koordinaatit, Koordinaatit maali) {
        return Math.max(Math.abs(koordinaatit.getX() - maali.getX()), Math.abs(koordinaatit.getY() - maali.getY())) * 1.001;
    }

}
