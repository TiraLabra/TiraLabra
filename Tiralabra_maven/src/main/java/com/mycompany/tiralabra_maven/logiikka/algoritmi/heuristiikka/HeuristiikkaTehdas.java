package com.mycompany.tiralabra_maven.logiikka.algoritmi.heuristiikka;

import com.mycompany.tiralabra_maven.HeuristiikkaTyyppi;

/**
 * Tehdasluokka joka palauttaa halutunlaisen heuristiikan tyypin perusteella.
 * @author mikko
 */
public class HeuristiikkaTehdas {

    /**
     * Palauttaa heuristiikan parametrina annetun tyypin perusteella.
     * @param tyyppi
     * @return heuristiikka
     */
    public Heuristiikka getHeuristiikka(HeuristiikkaTyyppi tyyppi) {
        switch (tyyppi) {
            case MANHATTAN:
                return new ManhattanHeuristiikka();
            case MANHATTAN_TIEBREAKING:
                return new ManhattanTieBreakingHeuristiikka();
            case DIAGONAALINEN:
                return new DiagonaalinenHeuristiikka();
            case DIAGONAALINEN_TIEBREAKING:
                return new DiagonaalinenTieBreakingHeuristiikka();
        }
        return null;
    }
}
