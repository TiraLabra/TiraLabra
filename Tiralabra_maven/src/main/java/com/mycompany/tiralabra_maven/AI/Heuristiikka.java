package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.peli.Nappula;
import com.mycompany.tiralabra_maven.peli.Pelilauta;

/**
 * Luokka toteuttaa joidenkin tekoälyjen vaatiman heuristiikan eli selvittää
 * pelitilanteen arvon, jotta erilaisten siirtojen keskinäinen vertailu olisi
 * mahdollista
 *
 * @author noora
 */
public class Heuristiikka {

    private final Pelilauta lauta;

    public Heuristiikka(Pelilauta lauta) {
        this.lauta = lauta;
    }

    /**
     * Metodi laskee pelilaudan arvon sillä olevien nappuloiden määrän,
     * sijainnin ja liikkumismahdollisuuksien perusteella
     *
     * @param valkoisenVuoroSiirtaa Kertoo kumman pelaajan vuoro on kyseessä
     * @return Palauttaa pelilaudan arvoa kuvaavan luvun
     */
    public int laskeTilanteenArvo(boolean valkoisenVuoroSiirtaa) {
        Nappula[][] ruudukko = lauta.getRuudukko();
        int pisteet = 0;
        Nappula nappula = Nappula.MUSTA;
        Nappula kruunattu = Nappula.KRUUNATTU_MUSTA;
        if (valkoisenVuoroSiirtaa) {
            nappula = Nappula.VALKOINEN;
            kruunattu = Nappula.KRUUNATTU_VALKOINEN;
        }
        for (int rivi = 0; rivi < 8; rivi++) {
            for (int sarake = 0; sarake < 8; sarake++) {
                if (ruudukko[rivi][sarake] == null) {
                    pisteet += 10;
                    continue;
                }
                if (ruudukko[rivi][sarake].equals(nappula)) {
                    pisteet += 100;
                    pisteet += laskePisteetRiveista(nappula, rivi);
                } else if (ruudukko[rivi][sarake].equals(kruunattu)) {
                    pisteet += 200;
                }
                pisteet += laskePisteetSarakkeista(sarake);
                pisteet = laskePisteetLiikkumismahdollisuuksista(valkoisenVuoroSiirtaa, rivi, sarake, pisteet);
            }
        }
        pisteet += laskePisteetTyhjistaPaikoistaKruunausrivilla(valkoisenVuoroSiirtaa, ruudukko);

        return pisteet;
    }

    private int laskePisteetLiikkumismahdollisuuksista(boolean valkoisenVuoroSiirtaa, int rivi, int sarake, int pisteet) {
        if (lauta.voikoNappulaLiikkua(valkoisenVuoroSiirtaa, rivi, sarake, rivi + 1, sarake + 1)) {
            pisteet++;
        }
        if (lauta.voikoNappulaLiikkua(valkoisenVuoroSiirtaa, rivi, sarake, rivi - 1, sarake + 1)) {
            pisteet++;
        }
        if (lauta.voikoNappulaLiikkua(valkoisenVuoroSiirtaa, rivi, sarake, rivi + 1, sarake - 1)) {
            pisteet++;
        }
        if (lauta.voikoNappulaLiikkua(valkoisenVuoroSiirtaa, rivi, sarake, rivi - 1, sarake - 1)) {
            pisteet++;
        }
//                if (lauta.voikoNappulaHypata(valkoisenVuoroSiirtaa, rivi, sarake, rivi + 1, sarake + 1, rivi + 2, sarake + 2)) {
//                    pisteet += 2;
//                }
//                if (lauta.voikoNappulaHypata(valkoisenVuoroSiirtaa, rivi, sarake, rivi - 1, sarake + 1, rivi - 2, sarake + 2)) {
//                    pisteet += 2;
//                }
//                if (lauta.voikoNappulaHypata(valkoisenVuoroSiirtaa, rivi, sarake, rivi + 1, sarake - 1, rivi + 2, sarake - 2)) {
//                    pisteet += 2;
//                }
//                if (lauta.voikoNappulaHypata(valkoisenVuoroSiirtaa, rivi, sarake, rivi - 1, sarake - 1, rivi - 2, sarake - 2)) {
//                    pisteet += 2;
//                       }
        return pisteet;
    }

    private int laskePisteetRiveista(Nappula nappula, int rivi) {
        int pisteet = 0;
        if (nappula.equals(Nappula.MUSTA)) {
            pisteet += rivi;
        } else {
            pisteet = pisteet + (7 - rivi);
        }
        return pisteet;
    }

    private int laskePisteetSarakkeista(int sarake) {
        int pisteet = 0;
        if (sarake == 0 || sarake == 7) {
            pisteet += 6;
        } else if (sarake == 1 || sarake == 6) {
            pisteet += 3;
        } else if (sarake == 2 || sarake == 5) {
            pisteet += 2;
        } else {
            pisteet += 1;
        }
        return pisteet;
    }
    
    private int laskePisteetTyhjistaPaikoistaKruunausrivilla(boolean valkoisenVuoroSiirtaa, Nappula[][] ruudukko){
        int pisteet = 0;
        if (valkoisenVuoroSiirtaa){
            for (int i = 1; i < 8; i += 2){
                if (ruudukko[0][i] == null){
                    pisteet++;
                }
            }
        } else {
            for (int j = 0; j < 8; j += 2){
                if (ruudukko[7][j] == null){
                    pisteet++;
                }
            }
        }
        return pisteet;
    }

}
