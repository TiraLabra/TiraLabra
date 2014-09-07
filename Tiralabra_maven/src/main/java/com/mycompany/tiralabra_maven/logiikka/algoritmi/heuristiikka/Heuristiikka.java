package com.mycompany.tiralabra_maven.logiikka.algoritmi.heuristiikka;

import com.mycompany.tiralabra_maven.Koordinaatit;

/**
 * Heuristiikka arvioi etäisyyden kahden pisteen välillä.
 * @author mikko
 */
public interface Heuristiikka {
    /**
     * Arvioi parametreinä annettujen koordinaattien etäisyyden maaliin.
     * @param koordinaatit
     * @param maali
     * @return arvioitu etäisyys maaliin
     */
    public abstract double arvioiMatkaMaaliin(Koordinaatit koordinaatit, Koordinaatit maali);
}
