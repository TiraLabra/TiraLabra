/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.gui;

import java.awt.Color;
import java.util.EnumMap;
import java.util.Map;

/**
 * Ruutu, joista maailma koostuu. Voi olla LATTIA, SEINA tai VESI.
 *
 * @author mikko
 */
public enum Ruutu implements PiirrettavaRuutu {

    LATTIA, RUOHO, HIEKKA, VESI, SEINA;
    
    private static final Color keltainenVari;
    private static final Color vihreaVari;
    private static final Map<Ruutu, Integer> kustannukset;
    
    static{
        keltainenVari = new Color(0xFFD699);
        vihreaVari = new Color(0xADEBAD);
        kustannukset = new EnumMap<>(Ruutu.class);
        kustannukset.put(LATTIA, 1);
        kustannukset.put(RUOHO, 2);
        kustannukset.put(HIEKKA, 3);
        kustannukset.put(VESI, 20);
        kustannukset.put(SEINA, 0);
    }

    /**
     * Palauttaa ruutua vastaavan värin.
     *
     * @return väri
     */
    @Override
    public Color getVari() {
        switch (this) {
            case LATTIA:
                return Color.LIGHT_GRAY;
            case RUOHO:
                //return Color.GREEN;
                return vihreaVari;
            case HIEKKA:
                return keltainenVari;
                //return Color.YELLOW;
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
     * @return kustannus
     */
    public int getHinta() {
        return kustannukset.get(this);
    }
    
    /**
     * Asettaa kustannuksen ruudulle
     * @param ruutu
     * @param kustannus 
     */
    public static void asetaKustannus(Ruutu ruutu, int kustannus) {
        kustannukset.put(ruutu, kustannus);
    }
}
