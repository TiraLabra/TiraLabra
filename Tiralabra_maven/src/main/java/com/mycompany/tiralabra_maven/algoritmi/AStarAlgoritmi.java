/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.algoritmi;

import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.Suunta;
import com.mycompany.tiralabra_maven.gui.RuudunTila;
import com.mycompany.tiralabra_maven.gui.Ruutu;
import com.mycompany.tiralabra_maven.tietorakenteet.PrioriteettiKeko;
import java.util.ArrayList;

/**
 * Algoritmi-luokka sisältää varsinaisen reittialgoritmin suoritettavana
 * säikeenä. Säikeen käynnistäminen (start-metodin kutsu) käynnistää algoritmin
 * suorituksen.
 *
 * @author mikko
 *
 */
public class AStarAlgoritmi extends Algoritmi {

    private int[][] parhaatReitit;
    private Heuristiikka heuristiikka;
    private PrioriteettiKeko<Solmu> tutkittavat;
    private Solmu tutkittavaSolmu;

    /**
     * Konstruktorissa annetaan parametrina tieto siitä, halutaanko hidastettu
     * vai nopea simulaatio.
     *
     * @param maailma algoritmin toimintaympäristö, joka sisältää tiedon
     * ruutujen kustannuksista
     * @param hidaste odotetaan näin monta millisekuntia jokaisen algoritmin
     * suoritusaskeleen välillä.
     * @param alku alkupisteen koordinaatit
     * @param maali maalipisteen koordinaatit
     * @param vinottain sallitaanko liikkuminen vinottain
     * @param heuristiikka käytettävä heuristiikka
     */
    public AStarAlgoritmi(Ruutu[][] maailma, int hidaste, Koordinaatit alku, Koordinaatit maali, boolean vinottain, Heuristiikka heuristiikka) {
        super(maailma, hidaste, alku, maali, vinottain);
        this.heuristiikka = heuristiikka;
        this.parhaatReitit = new int[korkeus][leveys];
        
        alustaParhaatReitit();
        //Tehdään priorityQueue joka palauttaa aina sen solmun, jolle (etäisyys alkuun + arvioitu etäisyys loppuun) on pienin
        AStarVertailija vertailija = new AStarVertailija(this.heuristiikka, maali);
        tutkittavat = new PrioriteettiKeko<>(vertailija);
    }

    @Override
    public void run() {
        tutkittavat.lisaa(new Solmu(alku, 0, null));

        while (!tutkittavat.tyhja() && jatketaanko) {

            //Otetaan jonosta solmu
            tutkittavaSolmu = tutkittavat.seuraava();

            //Jos ollaan maalissa, lopetetaan tähän
            if (tutkittavaSolmu.getKoord().equals(maali)) {
                maaliLoytyi(tutkittavaSolmu);
                return;
            }

            //Muussa tapauksessa merkitään solmu nyt käsittelyssä olevaksi
            ruutujenTilat[tutkittavaSolmu.getKoord().getY()][tutkittavaSolmu.getKoord().getX()] = RuudunTila.KASITTELYSSA;

            //Odotetaan mahdollisen viiveen verran aikaa ennen jatkamista
            super.odota();

            //Käydään läpi solmun naapurit
            for (Solmu s : solmunNaapurit(tutkittavaSolmu)) {

                //Jos tähän ruutuun on jo päästy vähintään yhtä hyvää reittiä pitkin, ei käsitellä tätä enää
                if (parhaatReitit[s.getKoord().getY()][s.getKoord().getX()] != -1 && parhaatReitit[s.getKoord().getY()][s.getKoord().getX()] <= s.getKuljettuMatka()) {
                    continue;
                }
                //Muussa tapauksessa päivitetään paras reitti ja lisätään tämä tutkittaviin
                parhaatReitit[s.getKoord().getY()][s.getKoord().getX()] = s.getKuljettuMatka();

                ruutujenTilat[s.getKoord().getY()][s.getKoord().getX()] = RuudunTila.TUTKITTAVA;
                tutkittavat.lisaa(new Solmu(s.getKoord(), tutkittavaSolmu.getKuljettuMatka() + maailma[s.getKoord().getY()][s.getKoord().getX()].getHinta(), tutkittavaSolmu));
            }

            //Lopuksi merkitään tämä solmu tutkituksi
            ruutujenTilat[tutkittavaSolmu.getKoord().getY()][tutkittavaSolmu.getKoord().getX()] = RuudunTila.TUTKITTU;
        }

    }

    private void alustaParhaatReitit() {
        for (int x = 0; x < this.leveys; x++) {
            for (int y = 0; y < this.korkeus; y++) {
                parhaatReitit[y][x] = -1;
            }
        }
    }

}
