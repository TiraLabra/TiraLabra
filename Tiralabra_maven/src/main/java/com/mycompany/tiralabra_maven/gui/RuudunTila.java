package com.mycompany.tiralabra_maven.gui;

import java.awt.Color;

/**
 * Enum joka kertoo ruudun tilan algoritmin toimintaan liittyen.
 * @author mikko
 */
public enum RuudunTila implements PiirrettavaRuutu {

    TUTKITTAVA, TUTKITTU, REITTI, KASITTELYSSA;

    /**
     * Palauttaa ruudu tilaa vastaavan värin.
     * @return väri
     */
    @Override
    public Color getVari() {
        switch (this) {
            case TUTKITTAVA:
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

