package com.mycompany.tiralabra_maven.logiikka.algoritmi.heuristiikka;

import com.mycompany.tiralabra_maven.Koordinaatit;

/**
 * Heuristiikka-luokan perivä luokka, joka arvioi matkan maaliin käyttäen
 * manhattan-etäisyyttä, eli x- ja y-koordinaattien erotusten itseisarvojen
 * summaa.
 *
 * @author mikko
 */
public class ManhattanHeuristiikka implements Heuristiikka {

    /**
     * Palauttaa manhattan-etäisyyden parametrina annettujen koordinaattien ja
     * maalin välille.
     *
     * @param koord
     * @param maali
     * @return arvioitu matka maaliin
     */
    @Override
    public double arvioiMatkaMaaliin(Koordinaatit koord, Koordinaatit maali) {
        return Math.abs(koord.getX() - maali.getX()) + Math.abs(koord.getY() - maali.getY());
    }

}
