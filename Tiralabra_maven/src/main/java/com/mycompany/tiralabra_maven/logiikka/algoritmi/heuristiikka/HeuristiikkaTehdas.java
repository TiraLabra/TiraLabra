/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.logiikka.algoritmi.heuristiikka;

import com.mycompany.tiralabra_maven.HeuristiikkaTyyppi;

/**
 *
 * @author mikko
 */
public class HeuristiikkaTehdas {

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
