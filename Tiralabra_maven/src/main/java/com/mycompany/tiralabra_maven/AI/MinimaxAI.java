package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.Peli;
import com.mycompany.tiralabra_maven.Pelilauta;
import com.mycompany.tiralabra_maven.Siirto;

/**
 * Luokka toteuttaa minimax-algoritmia käyttävän tekoälyn
 * @author noora
 */
public class MinimaxAI extends AI {
    private Heuristiikka heuristiikka;

    public MinimaxAI(Peli peli) {
        super(peli);
    }

    /**
     * Metodi sisältää minimax-algoritmin
     * @param lauta Kyseisen pelitilanteen sisältävä pelilauta
     * @param syvyys Kuinka monta siirtoa eteenpäin pelitilanteita lasketaan
     * @param valkoisenVuoroSiirtaa Kumman pelaajan vuoro on kyseessä
     * @param vuorossaOlevaPelaaja Lasketaanko nyt vuorossa olevan pelaajan vai vastustajan siirtoa
     * @return Parhaan mahdollisen laudan arvon, joka voidaan saavuttaa tekemällä siirtoja kyseiselle pelilaudalle
     */
    private int minimax(Pelilauta lauta, int syvyys, boolean valkoisenVuoroSiirtaa, boolean vuorossaOlevaPelaaja) {
        int parasArvo, arvo;
        Siirto[] siirrot = lauta.getSallitutSiirrot(valkoisenVuoroSiirtaa);
        heuristiikka = new Heuristiikka(lauta);
        if (syvyys <= 0 || lauta.getSallitutSiirrot(valkoisenVuoroSiirtaa) == null) {
            return heuristiikka.laskeTilanteenArvo(valkoisenVuoroSiirtaa);
        }
        if (vuorossaOlevaPelaaja) {
            parasArvo = -1000000;
            for (int i = 0; i < siirrot.length; i++) {
                Pelilauta kopio = lauta.teeKopio();
                kopio.teeSiirto(siirrot[i]);
                arvo = minimax(kopio, syvyys - 1, valkoisenVuoroSiirtaa, false);
                if (arvo > parasArvo) {
                    parasArvo = arvo;
                }
            }
            return parasArvo;
        } else {
            parasArvo = 1000000;
            for (int i = 0; i < siirrot.length; i++) {
                Pelilauta kopio = lauta.teeKopio();
                kopio.teeSiirto(siirrot[i]);
                arvo = minimax(kopio, syvyys - 1, valkoisenVuoroSiirtaa, true);
                if (arvo < parasArvo) {
                    parasArvo = arvo;
                }
            }
            return parasArvo;
        }
    }

    /**
     * Metodi selvittää minkä siirron tekoäly tekee suraavaksi käyttäen apuna minimax-algoritmia.
     * Algoritmin avulla pisteytetään mahdolliset siirrot ja valitaan niistä tehtäväksi se, jonka saama pistemäärä on suurin
     * @return Palauttaa siirron, jonka tekoäly haluaa tehdä
     */
    @Override
    public Siirto seuraavaSiirto() {
        Siirto[] siirrot = this.peli.getSallitutSiirrot();
        int[] pisteet = new int[siirrot.length];
        for (int i = 0; i < siirrot.length; i++) {
            pisteet[i] = minimax(this.peli.getPelilauta().teeKopio(), 1, this.peli.isValkoisenVuoroSiirtaa(), true);
        }
        int suurinPistemaara = 0;
        int paikka = 0;
        for (int j = 0; j < pisteet.length; j++) {
            if (pisteet[j] > suurinPistemaara) {
                suurinPistemaara = pisteet[j];
                paikka = j;
            }
        }
        return siirrot[paikka];
    }
}
