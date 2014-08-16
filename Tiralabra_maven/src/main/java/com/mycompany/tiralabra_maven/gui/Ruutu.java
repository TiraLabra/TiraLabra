/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.gui;

import java.awt.Color;

/**
 *
 * @author mikko
 */
public enum Ruutu implements PiirrettavaRuutu {

    LATTIA, SEINA, VESI;

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
