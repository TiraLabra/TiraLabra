package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.Nappula;
import com.mycompany.tiralabra_maven.Pelilauta;

/**
 * Luokka toteuttaa tekoälyn vaatiman heuristiikan eli selvittää pelitilanteen arvon, jotta erilaisten siirtojen vertailu olisi mahdollista
 * @author noora
 */
public class Heuristiikka {
    private final Pelilauta lauta;
    
    public Heuristiikka(Pelilauta lauta){
        this.lauta = lauta;
    }
    
    /**
     * Metodi laskee pelilaudan arvon sillä olevien nappuloiden määrän, sijainnin ja liikkumismahdollisuuksien perusteella
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
                    continue;
                }
                if (ruudukko[rivi][sarake].equals(nappula)) {
                    pisteet++;
                    if (nappula.equals(Nappula.MUSTA)){
                        pisteet += sarake;
                    } else {
                        pisteet = pisteet + (7-sarake);
                    }
                } else if (ruudukko[rivi][sarake].equals(kruunattu)) {
                    pisteet += 2;
                }
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
//                }
            }
        }

        return pisteet;
    }

    
}
