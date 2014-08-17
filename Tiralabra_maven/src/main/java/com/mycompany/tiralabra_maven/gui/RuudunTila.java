/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.gui;

import java.awt.Color;

/**
 * Enum joka kertoo ruudun tilan algoritmin toimintaan liittyen.
 * @author mikko
 */
public enum RuudunTila implements PiirrettavaRuutu {

    KOSKEMATON, TUTKIMATON, TUTKITTU, REITTI, KASITTELYSSA;

    /**
     * Palauttaa ruudu tilaa vastaavan värin.
     * @return 
     */
    @Override
    public Color getVari() {
        switch (this) {
            case KOSKEMATON:
                return Color.LIGHT_GRAY;
            case TUTKIMATON:
                return Color.PINK;
            case TUTKITTU:
                return Color.CYAN;
            case REITTI:
                return Color.YELLOW;
            case KASITTELYSSA:
                return Color.ORANGE;
        }
        return null;
    }
}

