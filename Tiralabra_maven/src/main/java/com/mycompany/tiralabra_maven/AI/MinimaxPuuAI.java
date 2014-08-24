
package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.Lista;
import com.mycompany.tiralabra_maven.Peli;
import com.mycompany.tiralabra_maven.Pelilauta;
import com.mycompany.tiralabra_maven.Siirto;
import com.mycompany.tiralabra_maven.SolmujenVertailija;

/**
 * Luokka on vastaava kuin Minimax-luokka, mutta luokassa hyödynnetään erilaisia omia tietorakenteita
 * @author noora
 */
public class MinimaxPuuAI extends Pelaaja {
    private Heuristiikka heuristiikka;
    private int syvyys;

    public MinimaxPuuAI(Peli peli, int syvyys) {
        super(peli);
        this.syvyys = syvyys;
    }


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
     * Metodin avulla selvitetään, mikä siirto kannattaa seuraavaksi tehdä.
     * Selvityksessä käytetään apuna minimax-algoritmia ja heuristiikkaa
     * @param sallitutSiirrot Lista sallituista siirroista
     * @return Palauttaa seuraavaksi tehtävän siirron
     */
    @Override
    public Siirto seuraavaSiirto(Siirto[] sallitutSiirrot) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        
        }
        Lista<Solmu> lista = new Lista(new SolmujenVertailija());
        for (int i = 0; i < sallitutSiirrot.length; i++) {
            lista.lisaa(new Solmu(minimax(this.peli.getPelilauta().teeKopio(), 1, this.peli.isValkoisenVuoroSiirtaa(), true), sallitutSiirrot[i]));
        }
        Solmu palautus = lista.getSuurin();
        return palautus.getSiirto();
    }
}
