package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.Peli;
import com.mycompany.tiralabra_maven.Pelilauta;
import com.mycompany.tiralabra_maven.Siirto;

/**
 * Luokka toteuttaa tekoälyn, joka palauttaa sen siirron joka johtaa parhaaseen mahdolliseen tilanteeseen laudalla
 * @author noora
 */
public class SeuraavanSiirronPisteetAI extends Pelaaja {
    private Heuristiikka heuristiikka;
    
    public SeuraavanSiirronPisteetAI(Peli peli){
        super(peli);
    }

    /**
     * Metodi palauttaa mahdollisista siirroista sen siirron, joka johtaa kyseisen pelaajan kannalta mahdollisimman arvokkaaseen tilanteeseen pelilaudalla
     * @return Siirto, jonka tekoäly haluaa tehdä
     */
    @Override
    public Siirto seuraavaSiirto(Siirto[] sallitutSiirrot) {
        boolean valkoisenVuoroSiirtaa = this.peli.isValkoisenVuoroSiirtaa();
        Pelilauta lauta;
        int[] pisteet = new int[sallitutSiirrot.length];
        for (int i = 0; i < sallitutSiirrot.length; i++){
            lauta = this.peli.getPelilauta().teeKopio();
            lauta.teeSiirto(sallitutSiirrot[i]);
            heuristiikka = new Heuristiikka(lauta);
            pisteet[i] = heuristiikka.laskeTilanteenArvo(valkoisenVuoroSiirtaa);
        }
        int suurinPistemaara = 0;
        int paikka = 0;
        for (int j = 0; j < pisteet.length; j++){
            if (pisteet[j] > suurinPistemaara){
                suurinPistemaara = pisteet[j];
                paikka = j;
            }
        }
        return sallitutSiirrot[paikka];
    }
    
}
