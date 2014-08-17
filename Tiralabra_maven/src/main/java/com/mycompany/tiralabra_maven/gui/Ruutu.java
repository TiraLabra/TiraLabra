/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.gui;

import java.awt.Color;

/**
 * Ruutu, joista maailma koostuu. Voi olla LATTIA, SEINA tai VESI.
 *
 * @author mikko
 */
public enum Ruutu implements PiirrettavaRuutu {

    LATTIA, SEINA, VESI;

    /**
     * Palauttaa ruutua vastaavan värin.
     *
     * @return
     */
    @Override
    public Color getVari() {
        switch (this) {
            case LATTIA:
                return Color.LIGHT_GRAY;
            case SEINA:
                return Color.DARK_GRAY;
            case VESI:
                return Color.BLUE;
        }
        return null;
    }

    /**
     * Palauttaa tiedon siitä, paljonko tähän ruutuun siirtyminen "maksaa"
     * reittialgoritmille.
     *
     * @return
     */
    public int getHinta() {
        switch (this) {
            case LATTIA:
                return 1;
            case SEINA:
                return 0;
            case VESI:
                return 3;
        }
        return 0;
    }
}
