package com.mycompany.tiralabra_maven.logiikka.algoritmi.heuristiikka;

import com.mycompany.tiralabra_maven.Koordinaatit;

/**
 *
 * @author mikko
 */
public class ManhattanTieBreakingHeuristiikka implements Heuristiikka{
    /**
     * Palauttaa manhattan-etäisyyden parametrina annettujen koordinaattien ja
     * maalin välille.
     * 
     * Tämä heuristiikka on lisäksi ns. "tie breaking" eli "tasapelin rikkova"
     * heuristiikka. Tämä tarkoittaa sitä että heuristiikka arvioi etäisyyden
     * aavistuksen verran yläkanttiin, jolloin reittialgoritmissa suositaan
     * niitä solmuja joissa ollaan edetty vähän maalia kohti (sen sijaan että
     * kaikkien mahdollisten eri reittien välille syntyisi tasapeli, joka
     * johtaisi siihen että algoritmi tutkisi kaikki mahdolliset keskenään yhtä
     * hyvät reittivaihtoehdot).
     * 
     * @param koord
     * @param maali
     * @return arvioitu matka maaliin
     */
    @Override
    public double arvioiMatkaMaaliin(Koordinaatit koord, Koordinaatit maali) {
        return (Math.abs(koord.getX() - maali.getX()) + Math.abs(koord.getY() - maali.getY()))*1.001;
    }
}
